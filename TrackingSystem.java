package TrackingTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by test on 7/27/2015.
 */
public class TrackingSystem {

    List<TrackingTableRecord> records;

    public TrackingSystem(List<TrackingTableRecord> records) {
        this.records = records;
    }

    public List<TrackingTableRecord> compareTrackingRecords(List<TrackingTableRecord> records, TrackingTableRecord prev, TrackingTableRecord next){
        if (records.size() == 0){
            records.add(next);
            return records;
        }

        if (prev == null) {
            System.out.println("is null");
            return records;
        }

        List<TrackingTableRecord> updatedRecords = new ArrayList<TrackingTableRecord>();
        //System.out.println(next.getRange().classify(prev.getRange()));
        switch (next.getRange().classify(prev.getRange())){
            case SAME :
                for (TrackingTableRecord record: records)
                    if (record.equals(prev))
                        updatedRecords.add(next);
                    else
                        updatedRecords.add(record);
                break;
            case SUBSET :
                TrackingTableRecord leftRecord = new TrackingTableRecord(new Range(prev.getRange().lo, next.getRange().lo - 1), prev.statusCode, prev.transferCode);
                TrackingTableRecord rightRecord = new TrackingTableRecord(new Range(next.getRange().hi + 1, prev.getRange().hi), prev.statusCode, prev.transferCode);
                for (TrackingTableRecord record: records)
                    if (record.equals(prev)){
                        updatedRecords.add(leftRecord);
                        updatedRecords.add(next);
                        updatedRecords.add(rightRecord);
                    }
                    else
                        updatedRecords.add(record);
                break;
            case SUPERSET :
                TrackingTableRecord leftSplitRecord = new TrackingTableRecord(new Range(next.getRange().lo, prev.getRange().hi), next.statusCode, next.transferCode);
                TrackingTableRecord rightSplitRecord = new TrackingTableRecord(new Range(prev.getRange().hi + 1, next.getRange().hi), next.statusCode, next.transferCode);

                for (TrackingTableRecord record: records)
                    if (record.equals(prev))
                        updatedRecords.add(leftSplitRecord);
                    else
                        updatedRecords.add(record);

                compareTrackingRecords(updatedRecords, updatedRecords.get(updatedRecords.indexOf(leftSplitRecord)+1),rightSplitRecord);
                break;
            case LESSOVERLAP:
                TrackingTableRecord nonOverlappedRecord = new TrackingTableRecord(new Range(next.getRange().hi + 1, prev.getRange().hi), prev.statusCode, prev.transferCode);

                for (TrackingTableRecord record: records)
                    if (record.equals(prev)){
                        updatedRecords.add(next);
                        updatedRecords.add(nonOverlappedRecord);
                    }
                    else
                        updatedRecords.add(record);
                break;
            case MOREOVERLAP:
                TrackingTableRecord newPrevRecord = prev;
                newPrevRecord.setRange(new Range(prev.getRange().lo, next.getRange().lo - 1));
                TrackingTableRecord newNextRecord = next;
                newNextRecord.setRange(new Range(next.getRange().lo, prev.getRange().hi));
                TrackingTableRecord nonOverlappedNextRecord = new TrackingTableRecord(new Range(prev.getRange().hi + 1, next.getRange().hi), next.statusCode, next.transferCode);

                for (TrackingTableRecord record: records)
                    if (record.equals(prev)){
                        updatedRecords.add(newPrevRecord);
                        updatedRecords.add(newNextRecord);
                    }
                    else
                        updatedRecords.add(record);

                compareTrackingRecords(updatedRecords, updatedRecords.get(updatedRecords.indexOf(newNextRecord)+1),nonOverlappedNextRecord);

                break;
            case LESSDISJOINT:
                for (TrackingTableRecord record: records) {
                    if (record.equals(prev))
                        updatedRecords.add(next);
                    updatedRecords.add(record);
                }
                break;
            case MOREDISJOINT:
                for (TrackingTableRecord record: records) {
                    updatedRecords.add(record);
                    if (record.equals(prev))
                        updatedRecords.add(next);
                }
                break;

        }

        return updatedRecords;
    }

    public List<TrackingTableRecord> updateTable(){
        List<TrackingTableRecord> outputRecords = new ArrayList<TrackingTableRecord>();
        outputRecords.add(records.get(0));
        for (TrackingTableRecord record:records)
            outputRecords = compareTrackingRecords(outputRecords, outputRecords.get(0), record);

        return outputRecords;
    }

    public void print() {
        System.out.println("*************Input  Starts****************");
        for (TrackingTableRecord record: records)
            System.out.println(record);
        System.out.println("*************Input  Ends******************");
        System.out.println("*************Output Starts****************");
        for (TrackingTableRecord record: updateTable())
            System.out.println(record);
        System.out.println("*************Output Ends******************");

        TrackingTableRecord finalRecord;
        List<TrackingTableRecord> finalRecords = new ArrayList<TrackingTableRecord>();
        Range range = new Range(0,0);

        for(int j=0; j<updateTable().size();) {
            finalRecord = new TrackingTableRecord();
            int i = j+1;
            int count =0;
            range.lo = (updateTable().get(i - 1).getRange().lo);
            range.hi = updateTable().get(i - 1).getRange().hi;
            finalRecord.setStatusCode(updateTable().get(i - 1).getStatusCode());
            finalRecord.setTransferCode(updateTable().get(i - 1).getTransferCode());
            finalRecord.setRange(range);
            if(i == updateTable().size()){
                System.out.println(finalRecord);
                finalRecords.add(finalRecord);
                break;
            }
            while((updateTable().get(i-1).getRange().hi == (updateTable().get(i).getRange().lo - 1))
                    && (updateTable().get(i).getStatusCode() == updateTable().get(i-1).getStatusCode())
                    && (updateTable().get(i).getTransferCode() == updateTable().get(i-1).getTransferCode()))
            {
                range.hi = (updateTable().get(i).getRange().hi);
                count++;
                i++;
            }
            //System.out.println("count-"+count);
            finalRecord.setRange(range);
            System.out.println(finalRecord);
            finalRecords.add(finalRecord);
           /* System.out.println(finalRecord.getRange().lo + " " + finalRecord.getRange().hi
                    + " " + finalRecord.getStatusCode() + " " + finalRecord.getTransferCode());*/
            j = j+count+1;

        }


        /*System.out.println("*************Final Starts****************");
        System.out.println(finalRecords.size());
        for (TrackingTableRecord record: finalRecords)
            System.out.println(record);
        System.out.println("*************Final Ends******************");
*/

    }
    
}

