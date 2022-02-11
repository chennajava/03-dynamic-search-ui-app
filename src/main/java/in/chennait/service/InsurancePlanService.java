package in.chennait.service;

import java.util.List;

import in.chennait.request.SearchRequest;
import in.chennait.response.PlanResponse;

public interface InsurancePlanService {

	public List<PlanResponse> searchPlans(SearchRequest request);

	public List<String> getUniquePlanNames();

	public List<String> getIniquePlanStatus();
}
