package TrackingTable;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestClient {

	public static void main(String[] args) throws IOException {
		List<TrackingTableRecord> records = new ArrayList<TrackingTableRecord>();
//		//BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		Scanner stdin = new Scanner(new BufferedInputStream(System.in));
		String str = "1 10 A 1";
		//System.out.println(str);

		/*while ((str ) != null) {
			System.out.println(str);
			String[] s = str.split(" ");
			Range range = new Range(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
			char statusCode = s[2].charAt(0);
			int transferCode = Integer.parseInt(s[3]);
			TrackingTableRecord record = new TrackingTableRecord(range, statusCode, transferCode);
			records.add(record);
			str = null;
		}*/

		TrackingTableRecord record = new TrackingTableRecord(new Range(11,20), 'A', 1);
		records.add(record);
		record = new TrackingTableRecord(new Range(21,30), 'B', 1);
		records.add(record);
		record = new TrackingTableRecord(new Range(5,15), 'A', 2);
		records.add(record);
        record = new TrackingTableRecord(new Range(1,13), 'A', 2);
        records.add(record);
		TrackingSystem trackingSystem = new TrackingSystem(records);
		trackingSystem.updateTable();
		trackingSystem.print();
	}

}
