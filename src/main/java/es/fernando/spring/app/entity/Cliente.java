package es.fernando.spring.app.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

// TODO: Auto-generated Javadoc
/**
 * The Class Cliente.
 */
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** The nombre. */
	@NotEmpty
	private String nombre;

	/** The email. */
	@NotEmpty
	@Email
	private String email;

	/** The create at. */
	@Column(name = "create_at")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.DATE)
	private Date createAt;

	/** The foto. */
	@Column(name = "foto")
	private String foto;

	/** The facturas. */
	@JsonManagedReference
	@OneToMany(mappedBy="cliente", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)
	private List<Factura> facturas;

	/**
	 * Instantiates a new cliente.
	 */
	public Cliente() {
		facturas = new ArrayList<Factura>();
	}

	/**
	 * Pre persist.
	 */
	@PrePersist
	@PreUpdate
	public void prePersist() {
		createAt = new Date();
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the creates the at.
	 *
	 * @return the creates the at
	 */
	public Date getCreateAt() {
		return createAt;
	}

	/**
	 * Sets the creates the at.
	 *
	 * @param createAt the new creates the at
	 */
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	/**
	 * Gets the foto.
	 *
	 * @return the foto
	 */
	public String getFoto() {
		return foto;
	}

	/**
	 * Sets the foto.
	 *
	 * @param foto the new foto
	 */
	public void setFoto(String foto) {
		this.foto = foto;
	}

	/**
	 * Gets the facturas.
	 *
	 * @return the facturas
	 */
	public List<Factura> getFacturas() {
		return facturas;
	}

	/**
	 * Sets the facturas.
	 *
	 * @param facturas the new facturas
	 */
	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	/**
	 * Adds the factura.
	 *
	 * @param factura the factura
	 */
	public void addFactura(Factura factura) {
		facturas.add(factura);
	}

	@Override
	public String toString() {
		return nombre;
	}

	
	
}

