package ru.nsu.ashikhmin.music_studio_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.nsu.ashikhmin.music_studio_app.utils.SQLAdds;

@Data
@Getter
@Setter
@AllArgsConstructor
public class CustomDistributionServiceInputDto {

    private static final String ID_FILTRATION = "id";
    private static final String NAME_FILTRATION = "name";
    private static final String TYPE_FILTRATION = "type";
    private static final String COST_FILTRATION = "listenWatchCost";


    private String type;
    private Double lowestListenWatchCost;
    private Double highestListenWatchCost;
    private String filtrationField;

    @Override
    public String toString() {
        return "DistributionServiceCustomDto{" + "type=" + this.type + ", lowestListenWatchCost=" +
                this.lowestListenWatchCost + ", highestListenWatchCost=" + this.highestListenWatchCost + "}";
    }

    public boolean isNotEmpty() {
        return type != null || lowestListenWatchCost != null || highestListenWatchCost != null;
    }

    public boolean isType(){
        return type != null;
    }

    public boolean isListenWatchCost(){
        return lowestListenWatchCost != null || highestListenWatchCost != null;
    }

    public boolean isListenWatchCostAnd(){
        return lowestListenWatchCost != null && highestListenWatchCost != null;
    }

    public boolean isFiltrationField(){
        if(filtrationField == null){
            return false;
        }
        return filtrationField.equals(ID_FILTRATION) || filtrationField.equals(NAME_FILTRATION)
                || filtrationField.equals(TYPE_FILTRATION) || filtrationField.equals(COST_FILTRATION);
    }

    public String getFiltration(){
        if(filtrationField.equals(COST_FILTRATION)){
            return "listen_watch_cost";
        }
        return filtrationField;
    }

    public void addType(StringBuilder stringBuilder, Boolean[] isNotFirst){
        if (!isType()) {
            return;
        }
        if (isNotFirst[0]) {
            stringBuilder.append(" ")
                    .append(SQLAdds.AND);
        } else {
            isNotFirst[0] = true;
        }
        stringBuilder.append(" ")
                .append(SQLAdds.DISTRIBUTION_SERVICE_TABLE)
                .append(".type = '")
                .append(type)
                .append("'");
    }




}
