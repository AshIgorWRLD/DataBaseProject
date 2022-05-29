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
public class CustomVisitInputDto {

    private Double lowestLengthOfVisit;
    private Double highestLengthOfVisit;

    public boolean isNotEmpty() {
        return lowestLengthOfVisit != null || highestLengthOfVisit != null;
    }

    public boolean isLengthOfVisitAnd() {
        return lowestLengthOfVisit != null && highestLengthOfVisit != null;
    }

    public void addHaving(StringBuilder stringBuilder) {
        if (!isNotEmpty()) {
            return;
        }
        stringBuilder.append(SQLAdds.HAVING)
                .append(" ")
                .append(SQLAdds.MIN)
                .append("(")
                .append(SQLAdds.VISIT_SCHEDULE_TABLE)
                .append(".length_of_visit) ");

        if (isLengthOfVisitAnd()) {
            stringBuilder.append(" ")
                    .append(SQLAdds.BETWEEN)
                    .append(" ")
                    .append(lowestLengthOfVisit)
                    .append(" ")
                    .append(SQLAdds.AND)
                    .append(" ")
                    .append(highestLengthOfVisit);
        } else {
            if (lowestLengthOfVisit != null) {
                stringBuilder.append(" >= ")
                        .append(lowestLengthOfVisit);
            } else {
                stringBuilder.append(" <= ")
                        .append(highestLengthOfVisit);
            }
        }
    }
}
