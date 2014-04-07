package com.arrowfoodcouriers.arrowfood;

/**
 * Created by Ryan on 2/6/14.
 */
public class DrawerListObject {
    private String title;
    private int position;
    private int image;
    private boolean logoutOnly;
    private boolean loginOnly;
    private boolean isHeader;

    public DrawerListObject(String title, int position, int image, boolean logoutOnly, boolean loginOnly, boolean isHeader) {
        this.setTitle(title);
        this.setPosition(position);
        this.setImage(image);
        this.setLogoutOnly(logoutOnly);
        this.setLoginOnly(loginOnly);
        this.setHeader(isHeader);
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public boolean isLogoutOnly() {
		return logoutOnly;
	}

	public void setLogoutOnly(boolean loginOnly) {
		this.logoutOnly = loginOnly;
	}

	public boolean isLoginOnly() {
		return loginOnly;
	}

	public void setLoginOnly(boolean loginOnly) {
		this.loginOnly = loginOnly;
	}

	public boolean isHeader() {
		return isHeader;
	}

	public void setHeader(boolean isHeader) {
		this.isHeader = isHeader;
	}
}
