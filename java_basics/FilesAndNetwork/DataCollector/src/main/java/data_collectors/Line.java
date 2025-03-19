package data_collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Line {
    private String lineNumber;
    private String lineName;


    @Override
    public String toString() {
        return lineNumber + ": " + lineName;
    }

}
