public class TMARequestRow {
    private int requestRowId;
    private int requestId;
    private int itemId;
    private String unitOfMeasurement;
    private double quantity;
    private double priceWithoutVAT;
    private String comment;
    
    
    public TMARequestRow(int requestRowID, int requestID, int itemID, String unitOfMeasurement,
            double quantity, double priceWithoutVAT, String comment) {
    	
        this.requestRowId = requestRowID;
        this.requestId = requestID;
        this.itemId = itemID;
        this.unitOfMeasurement = unitOfMeasurement;
        this.quantity = quantity;
        this.priceWithoutVAT = priceWithoutVAT;
        this.comment = comment;
}

    
    public int getRequestRowId() {
        return requestRowId;
    }

    public void setRequestRowId(int requestRowId) {
        this.requestRowId = requestRowId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPriceWithoutVAT() {
        return priceWithoutVAT;
    }

    public void setPriceWithoutVAT(double priceWithoutVAT) {
        this.priceWithoutVAT = priceWithoutVAT;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

