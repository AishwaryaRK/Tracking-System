package TrackingTable;

import java.util.List;

/**
 * Created by test on 7/27/2015.
 */
public class TrackingTableRecord {
    char statusCode;
    int transferCode;
    private Range range;

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



    public void compareTrackingInputs(List<TrackingTableRecord> trackingTableRecords, TrackingTableRecord record){

        /*if (isLeftDisjoint())
        if (record == null){
            trackingTableRecords.add(next);
        }

        if (next.startingNumber > record.endingNumber){
            compareTrackingInputs(trackingTableRecords, trackingTableRecords.get(trackingTableRecords.indexOf(record) + 1), next);
        }

        if (next.endingNumber < record.startingNumber){
            List<TrackingTableRecord> updatedTrackingTableRecords = new ArrayList<TrackingTableRecord>();
            updatedTrackingTableRecords.add(next);
            updatedTrackingTableRecords.addAll(trackingTableRecords);
        }

        if (next.startingNumber < record.startingNumber)*/


    }

    public boolean isLeftDisjoint(TrackingTableRecord record){
        return false;
    }

    public boolean isLeftOverlap(TrackingTableRecord record){
        return false;
    }

    public boolean isSubSet(TrackingTableRecord record){
        return false;
    }

    public boolean isRightOverlap(TrackingTableRecord record){
        return false;
    }

    public boolean isRightDisjoint(TrackingTableRecord record){
        return false;
    }

}

