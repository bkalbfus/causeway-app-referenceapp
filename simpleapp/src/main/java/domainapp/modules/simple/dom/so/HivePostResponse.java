package domainapp.modules.simple.dom.so;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class HivePostResponse {

    private int id;
    private String jsonrpc;
    private Result result;

    // Method to deserialize JSON into this class
    public static HivePostResponse fromJson(String json) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, HivePostResponse.class);
    }

    // Nested Result class
    
    @Getter @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result {
        @JsonProperty("abs_rshares")
        private long absRShares;
        @JsonProperty("active_votes")
        private List<ActiveVote> activeVotes;
        @JsonProperty("allow_curation_rewards")
        private boolean allowCurationRewards;
        @JsonProperty("allow_replies")
        private boolean allowReplies;
        @JsonProperty("allow_votes")
        private boolean allowVotes;
        private String author;
        @JsonProperty("author_reputation")
        private long authorReputation;
        @JsonProperty("author_rewards")
        private int authorRewards;
        private String body;
        @JsonProperty("body_length")
        private int bodyLength;
        private String category;
        private int children;
        @JsonProperty("created")
        private String createdTime;
        private String title;

    }

    // Nested ActiveVote class
    @Getter @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ActiveVote {
        private int percent;
        private long reputation;
        private long rshares;
        private String time;
        private String voter;
        private int weight;

    }
}
