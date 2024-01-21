package com.anicaaz.leaguewarefx.ui.obj;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Events {
    @JsonProperty("Events")
    private List<Event> Events;
}
