/*
 *
 * Author: 										Mubasher Zeb Khan & Michele Sousa
 *
 * ID:											21694 & 21959
 *
 * Code Running Status							Perfect
 *
 *
 */

package vgc_Mubasher_Zeb_Khan_21694;

public class CoursesModel {

	private int ID;
	private String name;
	private String description;
	private String courseYear;
	private String studentID;
	private String classTiming;
	private int classRoomNo;
	private String createdAt;
	private String updatedAt;
	private String createdBy;
	private String updatedBY;
	private boolean isActive;
	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @return the courseYear
	 */
	public String getCourseYear() {
		return courseYear;
	}
	/**
	 * @return the studentID
	 */
	public String getStudentID() {
		return studentID;
	}
	/**
	 * @return the classTiming
	 */
	public String getClassTiming() {
		return classTiming;
	}
	/**
	 * @return the classRoomNo
	 */
	public int getClassRoomNo() {
		return classRoomNo;
	}
	/**
	 * @return the createdAt
	 */
	public String getCreatedAt() {
		return createdAt;
	}
	/**
	 * @return the updatedAt
	 */
	public String getUpdatedAt() {
		return updatedAt;
	}
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @return the updatedBY
	 */
	public String getUpdatedBY() {
		return updatedBY;
	}
	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @param courseYear the courseYear to set
	 */
	public void setCourseYear(String courseYear) {
		this.courseYear = courseYear;
	}
	/**
	 * @param studentID the studentID to set
	 */
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	/**
	 * @param classTiming the classTiming to set
	 */
	public void setClassTiming(String classTiming) {
		this.classTiming = classTiming;
	}
	/**
	 * @param classRoomNo the classRoomNo to set
	 */
	public void setClassRoomNo(int classRoomNo) {
		this.classRoomNo = classRoomNo;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @param updatedBY the updatedBY to set
	 */
	public void setUpdatedBY(String updatedBY) {
		this.updatedBY = updatedBY;
	}
	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
