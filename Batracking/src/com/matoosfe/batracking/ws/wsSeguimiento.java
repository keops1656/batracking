package com.matoosfe.batracking.ws;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import com.matoosfe.batracking.entidad.EBatracking;
import com.matoosfe.batracking.entidad.EClienteFinal;
import com.matoosfe.batracking.entidad.ESeguimiento;
import com.matoosfe.batracking.entidad.EstadosMetodos;
import com.matoosfe.batracking.modelo.ClienteFinal;
import com.matoosfe.batracking.modelo.Entidad;
import com.matoosfe.batracking.modelo.Producto;
import com.matoosfe.batracking.modelo.Seguimiento;
import com.matoosfe.batracking.modelo.Usuario;
import com.matoosfe.batracking.negocio.seguimiento.SeguimientoFacade;
import com.matoosfe.batracking.negocio.admin.EntidadFacade;
import com.matoosfe.batracking.negocio.admin.ProductoFacade;
import com.matoosfe.batracking.negocio.seguridad.UsuarioFacade;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
//import org.json.JSONObject;

@Stateless
@Path("wsSeguimiento")
public class wsSeguimiento {
	
	@EJB
	private SeguimientoFacade adminSeguimientoEJB;
	
	@EJB
	private EntidadFacade entidadEJB;
	
	@EJB
	private ProductoFacade adminProducto;
	
	@EJB
	private UsuarioFacade usuarioEJB;
	
	@EJB
	private ProductoFacade productoEJB;
	
	
	/*Listar Seguimiento de los productos de una entidad dado idEntidad y estadoSeguimiento*/
	@GET
    @Produces("application/json; charset=utf-8")
    @Path("buscarSeguimientosEntidad/{idEntidad}/{estadoSeguimiento}")
    public List<ESeguimiento> buscarSeguimientosEntidad(
    		@PathParam("idEntidad") int idEntidad,
            @PathParam("estadoSeguimiento") String estadoSeguimiento
    ) throws Exception {
			List<ESeguimiento> lstResultado = new ArrayList<>();
			Entidad entidad = new Entidad();
			entidad = entidadEJB.buscarPorId(idEntidad);
			List<Seguimiento> lstSeguimiento = adminSeguimientoEJB.buscar(entidad, estadoSeguimiento);
			if( !lstSeguimiento.isEmpty()) {
				for (Seguimiento seguimiento : lstSeguimiento) {
					ESeguimiento objESeguimiento = new ESeguimiento(seguimiento);
					lstResultado.add(objESeguimiento);
				}
			}
		return lstResultado;
    }
	
	//Guardar seguimiento de un producto
	@POST
	@Consumes("application/json; charset=utf-8")
	@Produces("application/json; charset=utf-8")
	@Path("guardarSeguimientoProducto")
	public EstadosMetodos guardarSeguimientoProducto(EBatracking objEBatracking) throws Exception {
			EstadosMetodos objResultado = new EstadosMetodos();
			try {
				Usuario usuarioSesion = usuarioEJB.buscarPorId(objEBatracking.getIdUsuario());
				Producto producto = adminProducto.buscarPorId(objEBatracking.getIdProducto());
				String strResultado = adminSeguimientoEJB.guardarWS(producto, usuarioSesion, objEBatracking.isBlRefrescar(), objEBatracking.getTipoBateria(), objEBatracking.getLongitud(), objEBatracking.getLatitud() );
				objResultado.setStrMensaje(strResultado);
				if(strResultado.equals("Bateria recibida")) {
					objResultado.setBlResultado(true);
				}
			}catch(Exception e) {
				objResultado.setBlResultado(false);
				objResultado.setStrMensaje(e.getMessage());
			}
			return objResultado;
	}
	
	//VENDEDOR
	//VENTA, MANTENIMIENTO Y GARANTÍA DE UNA BATERIA
		@GET
		@Consumes("application/json; charset=utf-8")
		@Produces("application/json; charset=utf-8")
		@Path("comprobarGarantiaMantenimiento/{idUsuario}/{codProducto}/{tipoTransaccion}")
		public EstadosMetodos comprobarGarantiaMantenimiento(
				@PathParam("idUsuario") int idUsuario,
				@PathParam("codProducto") String strCodigo,
				@PathParam("tipoTransaccion") String tipoTransaccion
		) throws Exception {
			EstadosMetodos objResultado = new EstadosMetodos();
			Producto producto = new Producto();
			try {
				producto = productoEJB.buscarProductoDadoCodigo(strCodigo);
				if( producto.getProdEstadoBateria().equals("EN FABRICA") ){
					objResultado.setStrMensaje("La Bateria aún no se ha vendido");
					return objResultado;
				}
			}catch(Exception e) {
				objResultado.setStrMensaje("No existe el código de la Bateria " + strCodigo);
				return objResultado;
			}
			
			try {
					
				boolean blResultado = adminSeguimientoEJB.verificarGarantiaMantenimiento(idUsuario, producto, tipoTransaccion);
				if(blResultado == true) {						
					objResultado.setBlResultado(true);
					objResultado.setStrMensaje("Transacción aprobada");
				}
				else {
					objResultado.setBlResultado(false);
					objResultado.setStrMensaje("Transacción no aprobada");
				}
			
			}catch(Exception e) {
				objResultado.setBlResultado(false);
				objResultado.setStrMensaje("Error: " + e.getMessage() );
			}
			return objResultado;
		}
	
	//VENDEDOR
	//VENTA, MANTENIMIENTO Y GARANTÍA DE UNA BATERIA
		@POST
		@Consumes("application/json; charset=utf-8")
		@Produces("application/json; charset=utf-8")
		@Path("realizarTransaccionBateria/{idUsuario}/{codProducto}/{tipoTransaccion}")
		public EstadosMetodos realizarTransaccionBateria(
				EClienteFinal objEClienteFinal,
				@PathParam("idUsuario") int idUsuario,
				@PathParam("codProducto") String strCodigo,
				@PathParam("tipoTransaccion") String tipoTransaccion
		) throws Exception {
				EstadosMetodos objResultado = new EstadosMetodos();
				int idProducto= 0;
				try {
					Producto producto = productoEJB.buscarProductoDadoCodigo(strCodigo);
					idProducto = producto.getIdProducto();
				}catch(Exception e) {
					objResultado.setStrMensaje("No existe el código de la Bateria " + strCodigo);
					return objResultado;
				}
				try {
					ClienteFinal clienteFinal = new ClienteFinal();
					clienteFinal.setCliApellidos(objEClienteFinal.getCliApellidos());
					clienteFinal.setCliEmail(objEClienteFinal.getCliEmail());
					clienteFinal.setCliNombres(objEClienteFinal.getCliNombres());
					clienteFinal.setCliObservacion(objEClienteFinal.getCliObservacion());
					clienteFinal.setCliTelefono(objEClienteFinal.getCliTelefono());
					clienteFinal.setCliVehiculoColor(objEClienteFinal.getCliVehiculoColor());
					clienteFinal.setCliVehiculoMarca(objEClienteFinal.getCliVehiculoMarca());
					clienteFinal.setCliVehiculoModelo(objEClienteFinal.getCliVehiculoModelo());
					clienteFinal.setCliVehiculoPlaca(objEClienteFinal.getCliVehiculoPlaca());
					clienteFinal.setIdCliente(0);
					
					String strResultado = adminSeguimientoEJB.guardarTransaccion(idUsuario, idProducto, tipoTransaccion, clienteFinal, objEClienteFinal.getCliFotoAuto() );
					objResultado.setStrMensaje(strResultado);
					if(strResultado.equals("Transacción guardada correctamente")) {						
						objResultado.setBlResultado(true);
					}
				}catch(Exception e) {
					objResultado.setStrMensaje("Error: " + e.getMessage() );
				}
				return objResultado;
		}
		
		//Guardar seguimiento de un producto
		@POST
		@Consumes("application/json; charset=utf-8")
		@Produces("application/json; charset=utf-8")
		@Path("refreshBateria")
		public EstadosMetodos refreshBateria(EBatracking objEBatracking) throws Exception {
			EstadosMetodos objResultado = new EstadosMetodos();
			try {
				Usuario usuarioSesion = usuarioEJB.buscarPorId(objEBatracking.getIdUsuario());
				Producto producto = adminProducto.buscarPorId(objEBatracking.getIdProducto());
				String strResultado = adminSeguimientoEJB.refrescarBateria(producto, usuarioSesion, objEBatracking.isBlRefrescar(), objEBatracking.getTipoBateria(), objEBatracking.getLongitud(), objEBatracking.getLatitud() );
				objResultado.setStrMensaje(strResultado);
				if(strResultado.equals("Bateria refrescada")) {
					objResultado.setBlResultado(true);
				}
			}catch(Exception e) {
				objResultado.setBlResultado(false);
				objResultado.setStrMensaje(e.getMessage());
			}
			return objResultado;
		}
		
		
	
}
