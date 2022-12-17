package com.splitwise.clone.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
	
	private Long txnId;
	private Long personId;
	private Long amount;
	private List<Long> loaners;

}
