package app.domain.model.Store;

import app.domain.model.LegacyData;

import java.util.ArrayList;

/**
 * The type Legacy data store.
 */
public class LegacyDataStore {

    /**
     * The Legacy data list.
     */
    public ArrayList<LegacyData> legacyDataList = new ArrayList<>();

    private LegacyData legacyData;

    /**
     * Gets legacy data list.
     *
     * @return the legacy data list
     */
    public ArrayList<LegacyData> getLegacyDataList() {
        return legacyDataList;
    }

    /**
     * Sets legacy data list.
     *
     * @param legacyDataList the legacy data list
     */
    public void setLegacyDataList(ArrayList<LegacyData> legacyDataList) {
        this.legacyDataList = legacyDataList;
    }

    /**
     * Add to legacy data boolean.
     *
     * @param legacyData the legacy data
     * @return the boolean
     */
    public boolean addToLegacyData(LegacyData legacyData){
        return legacyDataList.add(legacyData);
    }
}
