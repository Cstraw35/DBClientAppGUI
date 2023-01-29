package model;

import java.util.Calendar;
import java.util.Date;

public class User {
    private int userID;
    private String userName;
    private String password;
    private Date createDate;
    private String createdBy;
    private Date lastUpdate;
    private String lastUpdatedBy;

    public User() {
    }

    public User(int userID, String userName, String password, Date createDate, String createdBy, Date lastUpdate, String lastUpdatedBy) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the create date.
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @return created by
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @return last update
     */
    public Date getLastUpdate() {
        return lastUpdate;
    }

    /**
     * @return last updated by
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
}


