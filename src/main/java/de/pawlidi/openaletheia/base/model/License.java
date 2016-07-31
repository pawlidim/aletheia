package de.pawlidi.openaletheia.base.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.joda.time.DateTime;

/**
 * 
 * @author PAWLIDIM
 *
 *         Create: 00:27:27 02.05.2015
 *
 */
@XmlRootElement(name = "License")
public final class License implements Serializable {

	private String uuid;
	private String product;
	private String productVersion;
	private Long maxHost;
	private String address;
	private String owner;
	private String operatingSystem;
	private String company;
	private Long maxUser;
	private DateTime created;
	private DateTime modified;
	private DateTime dueDate;
	private String description;
	private Boolean remote;
	private String signature;
	private List<User> users = new ArrayList<User>();

	public License() {
		super();
	}

	public License(String uuid, String product, String productVersion, Long maxHost, String address, String owner,
			String operatingSystem, String company, Long maxUser, DateTime created, DateTime modified, DateTime dueDate,
			String description, Boolean remote) {
		super();
		this.uuid = uuid;
		this.product = product;
		this.productVersion = productVersion;
		this.maxHost = maxHost;
		this.address = address;
		this.owner = owner;
		this.operatingSystem = operatingSystem;
		this.company = company;
		this.maxUser = maxUser;
		this.created = created;
		this.modified = modified;
		this.dueDate = dueDate;
		this.description = description;
		this.remote = remote;
	}

	/**
	 * Getter to get the uuid
	 * 
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * Setter to set the uuid
	 * 
	 * @param uuid
	 *            the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * Getter to get the product
	 * 
	 * @return the product
	 */
	public String getProduct() {
		return product;
	}

	/**
	 * Setter to set the product
	 * 
	 * @param product
	 *            the product to set
	 */
	public void setProduct(String product) {
		this.product = product;
	}

	/**
	 * Getter to get the product version
	 * 
	 * @return the product version
	 */
	public String getProductVersion() {
		return productVersion;
	}

	/**
	 * Setter to set the product version
	 * 
	 * @param productVersion
	 *            the product version to set
	 */
	public void setProductVersion(String productVersion) {
		this.productVersion = productVersion;
	}

	/**
	 * Getter to get the max host
	 * 
	 * @return the max host
	 */
	public Long getMaxHost() {
		return maxHost;
	}

	/**
	 * Setter to set the max host
	 * 
	 * @param maxHost
	 *            the max host to set
	 */
	public void setMaxHost(Long maxHost) {
		this.maxHost = maxHost;
	}

	/**
	 * Getter to get the address
	 * 
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Setter to set the address
	 * 
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Getter to get the owner
	 * 
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * Setter to set the owner
	 * 
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * Getter to get the operatingSystem
	 * 
	 * @return the operatingSystem
	 */
	public String getOperatingSystem() {
		return operatingSystem;
	}

	/**
	 * Setter to set the operatingSystem
	 * 
	 * @param operatingSystem
	 *            the operatingSystem to set
	 */
	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	/**
	 * Getter to get the company
	 * 
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * Setter to set the company
	 * 
	 * @param company
	 *            the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * Getter to get the maxUser
	 * 
	 * @return the maxUser
	 */
	public Long getMaxUser() {
		return maxUser;
	}

	/**
	 * Setter to set the maxUser
	 * 
	 * @param maxUser
	 *            the maxUser to set
	 */
	public void setMaxUser(Long maxUser) {
		this.maxUser = maxUser;
	}

	/**
	 * Getter to get the created
	 * 
	 * @return the created
	 */
	public DateTime getCreated() {
		return created;
	}

	/**
	 * Setter to set the created
	 * 
	 * @param created
	 *            the created to set
	 */
	public void setCreated(DateTime created) {
		this.created = created;
	}

	/**
	 * Getter to get the modified
	 * 
	 * @return the modified
	 */
	public DateTime getModified() {
		return modified;
	}

	/**
	 * Setter to set the modified
	 * 
	 * @param modified
	 *            the modified to set
	 */
	public void setModified(DateTime modified) {
		this.modified = modified;
	}

	/**
	 * Getter to get the dueDate
	 * 
	 * @return the dueDate
	 */
	public DateTime getDueDate() {
		return dueDate;
	}

	/**
	 * Setter to set the dueDate
	 * 
	 * @param dueDate
	 *            the dueDate to set
	 */
	public void setDueDate(DateTime dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * Getter to get the description
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setter to set the description
	 * 
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getRemote() {
		return remote;
	}

	public void setRemote(Boolean remote) {
		this.remote = remote;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return product + "|" + company + "/" + created;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof License)) {
			return false;
		}
		License other = (License) obj;
		if (company == null) {
			if (other.getCompany() != null) {
				return false;
			}
		} else if (!company.equals(other.getCompany())) {
			return false;
		}
		if (product == null) {
			if (other.getProduct() != null) {
				return false;
			}
		} else if (!product.equals(other.getProduct())) {
			return false;
		}
		if (uuid == null) {
			if (other.getUuid() != null) {
				return false;
			}
		} else if (!uuid.equals(other.getUuid())) {
			return false;
		}
		return true;
	}

}
