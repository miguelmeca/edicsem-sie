package com.edicsem.pe.sie.util.form;

public class BaseMantenimientoForm {

    protected boolean editable = true;

    protected boolean newRecord = true;
    
	protected String selectedItem = null;
	
	private boolean modified = false; 

    /**
     * @return Returns the editable.
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     * @param editable
     *            The editable to set.
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    /**
     * @return Returns the newRecord.
     */
    public boolean isNewRecord() {
        return newRecord;
    }

    /**
     * @param newRecord
     *            The newRecord to set.
     */
    public void setNewRecord(boolean newRecord) {
        this.newRecord = newRecord;
    }

	/**
	 * @return Returns the modified.
	 */
	public boolean isModified() {
		return modified;
	}

	/**
	 * @param modified The modified to set.
	 */
	public void setModified(boolean modified) {
		this.modified = modified;
	}

	/**
	 * @return Returns the selectedItem.
	 */
	public String getSelectedItem() {
		return selectedItem;
	}

	/**
	 * @param selectedItem The selectedItem to set.
	 */
	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}
}
