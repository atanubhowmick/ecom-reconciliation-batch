/**
 * 
 */
package dev.atanu.ecom.reconciliation.batch.processor;

import org.springframework.batch.item.ItemProcessor;

import dev.atanu.ecom.reconciliation.entity.ProductEntity;

/**
 * @author Atanu Bhowmick
 *
 */
public class ProductReconciliationProcessor implements ItemProcessor<ProductEntity, String>{

	@Override
	public String process(ProductEntity item) throws Exception {
		return null;
	}

}
