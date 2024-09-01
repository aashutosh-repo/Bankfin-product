package com.fin.bancs.common;

import java.io.Serializable;

import lombok.Data;

@Data
public class Notifications  implements Serializable{
	private String notificationId;
    private String title;
    private String message;
    private boolean isActive;
}
