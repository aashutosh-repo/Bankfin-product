package com.fin.bancs.common;

import java.io.Serializable;
import java.util.Objects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode
public class GoldenCodePk implements Serializable{
	private int code_id;
	private int value;
}
