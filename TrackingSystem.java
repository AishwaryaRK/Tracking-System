package TrackingTable;

import java.util.List;

public class TrackingSystem {

    List<TrackingTableRecord> records;

    public void compareTrackingRecords(TrackingTableRecord prev, TrackingTableRecord next){
        switch (prev.getRange().classify(next.getRange())){
            case SAME : break;
            case SUBSET : break;
            case SUPERSET : break;
            case LESSOVERLAP: break;
            case MOREOVERLAP: break;
            case LESSDISJOINT: break;
            case MOREDISJOINT: break;

        }
    }
}