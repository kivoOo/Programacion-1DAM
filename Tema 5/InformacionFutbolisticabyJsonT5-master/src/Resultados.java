import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Resultados {

@SerializedName("count")
@Expose
private Integer count;
@SerializedName("filters")
@Expose
private Filters filters;
@SerializedName("matches")
@Expose
private List<Match> matches = null;

public Integer getCount() {
return count;
}

public void setCount(Integer count) {
this.count = count;
}

public Filters getFilters() {
return filters;
}

public void setFilters(Filters filters) {
this.filters = filters;
}

public List<Match> getMatches() {
return matches;
}

public void setMatches(List<Match> matches) {
this.matches = matches;
}

}