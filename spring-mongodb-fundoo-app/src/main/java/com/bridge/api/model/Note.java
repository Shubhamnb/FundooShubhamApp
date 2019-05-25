package com.bridge.api.model;


import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "note")
public class Note {
	@Id
	private String noteId;
	private String noteTitle;
	private String discription;
	private LocalDateTime updatedTime;
	private LocalDateTime currentTime;
	private String userId;
	
	public String getNoteId() {
		return noteId;
	}
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
	public String getNoteTitle() {
		return noteTitle;
	}
	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public LocalDateTime getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(LocalDateTime updatedTime) {
		this.updatedTime = updatedTime;
	}
	public LocalDateTime getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(LocalDateTime currentTime) {
		this.currentTime = currentTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	@Override
	public String toString() {
		return "Note [noteId=" + noteId + ", noteTitle=" + noteTitle + ", discription=" + discription + ", updatedTime="
				+ updatedTime + ", currentTime=" + currentTime + ", userId=" + userId + "]";
	}

	

}
