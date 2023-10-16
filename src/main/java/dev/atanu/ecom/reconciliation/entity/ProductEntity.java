/**
 * 
 */
package dev.atanu.ecom.reconciliation.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This entity class is mapped with the table PRODUCT_DETAILS in database. It
 * joins with other entity to fetch all the information.
 * 
 * @see BrandEntity
 * @see CategoryEntity
 * @see ColourEntity
 * @see AvailableProductEntity
 * 
 * @author Atanu Bhowmick
 *
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "SOLD_PRODUCT_DETAILS")
public class ProductEntity extends BaseEntity {

	private static final long serialVersionUID = -7149032080137456750L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_ID")
	private Long productId;

	@Column(name = "PRODUCT_NAME")
	private String productName;

	@Column(name = "PRODUCT_PRICE")
	private Double productPrice;

	@Column(name = "PRODUCT_SIZE")
	private String productSize;

	@Column(name = "PRODUCT_SOLD_QUANTITY")
	private Integer productSoldQuantity;
	
	@Column(name = "PRODUCT_SOLD_DATE")
	private LocalDate productSoldDate;
}
