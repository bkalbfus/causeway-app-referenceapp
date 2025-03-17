package domainapp.modules.hive.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HivePostJson {
	private long abs_rshares;
	private List<ActiveVote> active_votes;
	private boolean allow_curation_rewards;
	private boolean allow_replies;
	private boolean allow_votes;
	private String author;
	private long author_reputation;
	private int author_rewards;
	private List<Object> beneficiaries;
	private String body;
	private int body_length;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private String cashout_time;

	private String category;
	private int children;
	private long children_abs_rshares;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private String created;

	private String curator_payout_value;
	private int depth;
	private int id;
	private String json_metadata;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private String last_payout;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private String last_update;

	private String max_accepted_payout;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private String max_cashout_time;

	private long net_rshares;
	private int net_votes;
	private String parent_author;
	private String parent_permlink;
	private String pending_payout_value;
	private int percent_hbd;
	private String permlink;
	private String promoted;
	private List<Object> reblogged_by;
	private List<Object> replies;
	private int reward_weight;
	private String root_author;
	private String root_permlink;
	private String root_title;
	private String title;
	private String total_payout_value;
	private String total_pending_payout_value;
	private long total_vote_weight;
	private String url;
	private long vote_rshares;
	
	
@Getter
@Setter
public static class ActiveVote {
	private int percent;
	private long reputation;
	private long rshares;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private String time;

	private String voter;
	private int weight;
}
}


