package TrackingTable;

public class TrackingTableRecord implements Comparable<TrackingTableRecord> {
	private Range range;
	private char statusCode;
	private int transferCode;

	public TrackingTableRecord(Range range, char statusCode, int transferCode) {
		this.range = range;
		this.statusCode = statusCode;
		this.transferCode = transferCode;
	}

	public Range getRange() {
		return range;
	}

	public void setRange(Range range) {
		this.range = range;
	}

	public char getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(char statusCode) {
		this.statusCode = statusCode;
	}

	public int getTransferCode() {
		return transferCode;
	}

	public void setTransferCode(int transferCode) {
		this.transferCode = transferCode;
	}

	@Override
	public String toString() {
		return "TrackingTableRecord [range=" + range + ", statusCode=" + statusCode + ", transferCode=" + transferCode
				+ "]";
	}

	@Override
	public int compareTo(TrackingTableRecord other) {
		return range.compareTo(other.getRange());
	}

}
