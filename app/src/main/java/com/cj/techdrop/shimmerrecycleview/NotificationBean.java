package com.cj.techdrop.shimmerrecycleview;

public class NotificationBean {

	private int notificationId;
	private String notificationDetails;
	private String notificationType;
	private String notificationTitle;
	private String createdDTTM;
	private String receivedDTTM;
	private String userId;
	private String file_attach;
	private int isRead;


	private int deleteStatus;
	private boolean isChecked;

	public int getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(int deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	public int getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}
	public String getNotificationDetails() {
		return notificationDetails;
	}
	public void setNotificationDetails(String notificationDetails) {
		this.notificationDetails = notificationDetails;
	}
	public String getNotificationType() {
		return notificationType;
	}
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}
	public String getNotificationTitle() {
		return notificationTitle;
	}
	public void setNotificationTitle(String notificationTitle) {
		this.notificationTitle = notificationTitle;
	}
	public String getCreatedDTTM() {
		return createdDTTM;
	}
	public void setCreatedDTTM(String createdDTTM) {
		this.createdDTTM = createdDTTM;
	}
	public String getReceivedDTTM() {
		return receivedDTTM;
	}
	public void setReceivedDTTM(String receivedDTTM) {
		this.receivedDTTM = receivedDTTM;
	}
	public int getIsRead() {
		return isRead;
	}
	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFile_attach() {
		return file_attach;
	}
	public void setFile_attach(String file_attach) {
		this.file_attach = file_attach;
	}
}
