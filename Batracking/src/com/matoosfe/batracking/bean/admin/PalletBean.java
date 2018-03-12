package com.matoosfe.batracking.bean.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.tabview.TabView;
import org.primefaces.event.SelectEvent;

import com.matoosfe.batracking.modelo.Pallet;
import com.matoosfe.batracking.modelo.Producto;
import com.matoosfe.batracking.negocio.admin.PalletFacade;
import com.matoosfe.batracking.negocio.admin.ProductoFacade;
import com.matoosfe.kernel.web.bean.AbstractManagedBean;

/**
 * Clase para administrar el formulario de pallet
 * 
 * @author MATOOSFE - Ing. Marco Toscano Freire. Msc -martosfre 6 ago. 2017-
 *         10:33:42<br>
 *         <a href="mailto:mtoscano@matoosfe.com?Subject=Soporte Batracking"
 *         target="_top">Soporte</a><br>
 *         <a href="http://www.matoosfe.com">Matoosfe</a>
 */
@ManagedBean
@ViewScoped
public class PalletBean extends AbstractManagedBean {

	private Pallet pallet;
	private Pallet palletSel;
	private List<Pallet> listaPallet;
	private List<Producto> listaProductosSinPal;
	private List<Producto> listaProductosSel;
	private List<Producto> listaProductosEli;
	private String semillaBus;
	private int valorInicioBus;
	private int valorFinBus;

	@EJB
	private PalletFacade adminPallet;
	@EJB
	private ProductoFacade adminProducto;

	public PalletBean() {
		this.pallet = new Pallet();
		this.listaPallet = new ArrayList<>();
		this.listaProductosSinPal = new ArrayList<>();
		this.listaProductosEli = new ArrayList<>();
	}

	/**
	 * @return the pallet
	 */
	public Pallet getPallet() {
		return pallet;
	}

	/**
	 * @param pallet
	 *            the pallet to set
	 */
	public void setPallet(Pallet pallet) {
		this.pallet = pallet;
	}

	/**
	 * @return the palletSel
	 */
	public Pallet getPalletSel() {
		return palletSel;
	}

	/**
	 * @param palletSel
	 *            the palletSel to set
	 */
	public void setPalletSel(Pallet palletSel) {
		this.palletSel = palletSel;
	}

	/**
	 * @return the listaPallet
	 */
	public List<Pallet> getListaPallet() {
		return listaPallet;
	}

	/**
	 * @param listaPallet
	 *            the listaPallet to set
	 */
	public void setListaPallet(List<Pallet> listaPallet) {
		this.listaPallet = listaPallet;
	}

	/**
	 * @return the listaProductosSinPal
	 */
	public List<Producto> getListaProductosSinPal() {
		return listaProductosSinPal;
	}

	/**
	 * @param listaProductosSinPal
	 *            the listaProductosSinPal to set
	 */
	public void setListaProductosSinPal(List<Producto> listaProductosSinPal) {
		this.listaProductosSinPal = listaProductosSinPal;
	}

	/**
	 * @return the listaProductosSel
	 */
	public List<Producto> getListaProductosSel() {
		return listaProductosSel;
	}

	/**
	 * @param listaProductosSel
	 *            the listaProductosSel to set
	 */
	public void setListaProductosSel(List<Producto> listaProductosSel) {
		this.listaProductosSel = listaProductosSel;
	}

	/**
	 * @return the listaProductosEli
	 */
	public List<Producto> getListaProductosEli() {
		return listaProductosEli;
	}

	/**
	 * @param listaProductosEli
	 *            the listaProductosEli to set
	 */
	public void setListaProductosEli(List<Producto> listaProductosEli) {
		this.listaProductosEli = listaProductosEli;
	}

	/**
	 * @return the semillaBus
	 */
	public String getSemillaBus() {
		return semillaBus;
	}

	/**
	 * @param semillaBus
	 *            the semillaBus to set
	 */
	public void setSemillaBus(String semillaBus) {
		this.semillaBus = semillaBus;
	}

	/**
	 * @return the valorInicioBus
	 */
	public int getValorInicioBus() {
		return valorInicioBus;
	}

	/**
	 * @param valorInicioBus
	 *            the valorInicioBus to set
	 */
	public void setValorInicioBus(int valorInicioBus) {
		this.valorInicioBus = valorInicioBus;
	}

	/**
	 * @return the valorFinBus
	 */
	public int getValorFinBus() {
		return valorFinBus;
	}

	/**
	 * @param valorFinBus
	 *            the valorFinBus to set
	 */
	public void setValorFinBus(int valorFinBus) {
		this.valorFinBus = valorFinBus;
	}

	/**
	 * Método para guardar un pallet
	 */
	public void guardar() {
		try {
			if (pallet.getProductos() != null && !pallet.getProductos().isEmpty()) {
				if (pallet.getIdPallet() > 0) {
					adminPallet.actualizar(pallet, pallet.getProductos(), listaProductosEli);
				} else {
					adminPallet.guardar(pallet, pallet.getProductos());
				}
				addInfo("Pallet registrado correctamente");
				cargarPallet();
				cargarProductosSinPallet();
				resetearFormulario();
			} else {
				addError("Se debe seleccionar al menos un producto para el pallet");
			}
		} catch (Exception e) {
			addError("No se pudo registrar el pallet:" + e.getMessage());
		}
	}

	/**
	 * Método para editar un pallet
	 */
	public void editar() {
		if (palletSel != null) {
			this.pallet = palletSel;
			TabView tabViGen = (TabView) getCurrentContext().getViewRoot().findComponent(":frmGen:tabVieGen");
			tabViGen.setActiveIndex(0);
		} else {
			addError("Se debe seleccionar un pallet");
		}
	}

	/**
	 * Método para eliminar un pallet
	 */
	public void eliminar() {
		if (palletSel != null) {
			adminPallet.eliminar(palletSel, palletSel.getProductos());
			addInfo("Pallet eliminado correctamente");
			cargarPallet();
		} else {
			addError("Se debe seleccionar un pallet");
		}
	}

	/**
	 * Método para cargar los pallet
	 */
	private void cargarPallet() {
		try {
			this.listaPallet.clear();
			this.listaPallet = adminPallet.buscarTodos();
		} catch (Exception e) {
			addError("No se pudo cargar los pallets");
		}
	}

	/**
	 * Método para cargar productos sin pallet
	 */
	private void cargarProductosSinPallet() {
		try {
			this.listaProductosSinPal.clear();
			this.listaProductosSinPal = adminProducto.buscarProductosSinPallet();
		} catch (Exception e) {
			addError("No se pudo cargar los productos");
		}
	}

	/**
	 * Método para seleccionar un pallet
	 * 
	 * @param e
	 */
	public void seleccionarPallet(SelectEvent e) {
		this.palletSel = (Pallet) e.getObject();
	}

	/***
	 * Método para buscar los productos por un rango
	 */
	public void buscarProductos() {
		try {
			if (semillaBus != null && !semillaBus.equals("")) {
				List<String> codigos = new ArrayList<>();
				codigos.add("0");
				for (int i = valorInicioBus; i <= valorFinBus; i++) {
					codigos.add(semillaBus + i);
				}
				this.listaProductosSinPal = adminProducto.buscarProductosSinPallet(codigos);
				if (listaProductosSinPal.isEmpty()) {
					addInfo("No se encontraron productos con esos códigos");
				}
			} else {
				cargarProductosSinPallet();
			}
		} catch (Exception e) {
			addError("Error al consultar productos:" + e.getMessage());
		}

	}

	/**
	 * Método para asociar los productos al pallet
	 */
	public void procesarProductosPallet() {
		if (listaProductosSel != null && !listaProductosSel.isEmpty()) {
			this.pallet.setProductos(listaProductosSel);
			this.semillaBus = null;
			this.valorInicioBus = 0;
			this.valorFinBus = 0;
			this.listaProductosSel = null;
			cargarProductosSinPallet();
		}
	}

	/**
	 * Método para eliminar el producto del pallet
	 */
	public void eliminarProductoPallet(Producto productoSel) {
		this.pallet.getProductos().remove(productoSel);
		this.listaProductosEli.add(productoSel);
	}

	/**
	 * Método para resetear el formulario
	 */
	public void resetearFormulario() {
		this.pallet = new Pallet();
		this.palletSel = null;
		this.listaProductosSinPal.clear();
		this.semillaBus = null;
		this.valorInicioBus = 0;
		this.valorFinBus = 0;
		this.listaProductosSel = null;
	}

	@PostConstruct
	public void inicializar() {
		cargarPallet();
		cargarProductosSinPallet();
	}

}
