package com.ecommerce.order.dto;

import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDTO {

	private Long id;
	private Long userId;


	private List<OrdersDTO> orders;

}
