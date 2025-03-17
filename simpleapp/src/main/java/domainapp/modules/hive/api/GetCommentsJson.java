package domainapp.modules.hive.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCommentsJson {
	private int id;
	private String jsonrpc;
	private HivePostJson[] result;
}
