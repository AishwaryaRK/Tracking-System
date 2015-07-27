package TrackingTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TestClient {

	public static void main(String[] args) throws IOException {
		List<TrackingTableRecord> records = new ArrayList<TrackingTableRecord>();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String str = bufferedReader.readLine();
		while (str != null) {
			String[] s = str.split(" ");
			Range range = new Range(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
			char statusCode = s[2].charAt(0);
			int transferCode = Integer.parseInt(s[3]);
			TrackingTableRecord record = new TrackingTableRecord(range, statusCode, transferCode);
			records.add(record);
		}
		TrackingSystem trackingSystem = new TrackingSystem(records);
		trackingSystem.print();
	}

}
