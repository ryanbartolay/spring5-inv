package com.bartolay.inventory.enums;

/**
 * DO NOT REARRANGE THIS ORDER, as this same order is used in the database.table.transaction_id
 * You may refer to InventoryTransaction Entity
 * @author rbartolay
 *
 */
public enum TransactionType {
	SALES_INVOICE, // 0
	SALES_CANCEL_INVOICE, // 1
	SALES_RETURN, // 2
	SALES_RECIEVE, // 3
	STOCK_OPENING, // 4
	STOCK_TRANSFER, // 5
	STOCK_ADJUSTMENT; // 6
}
