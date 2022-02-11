package in.chennait.rest;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import in.chennait.exports.ExcelReportGenerator;
import in.chennait.exports.PdfReportGenerator;
import in.chennait.request.SearchRequest;
import in.chennait.response.PlanResponse;
import in.chennait.service.InsurancePlanService;

@RestController
public class InsurancePlanRestController {

	@Autowired
	private InsurancePlanService service;

	@GetMapping("/pdf")
	public void generatePdf(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=plans.pdf";
		response.setHeader(headerKey, headerValue);

		List<PlanResponse> plans = service.searchPlans(null);
		PdfReportGenerator pdfReport = new PdfReportGenerator();
		pdfReport.exportPdf(plans, response);
	}

	@GetMapping("/excel")
	public void generateExcel(HttpServletResponse response) throws Exception {
		response.setContentType("application/octer-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDataTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDataTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<PlanResponse> plans = service.searchPlans(null);
		ExcelReportGenerator excelReport = new ExcelReportGenerator();
		excelReport.export(plans, response);
	}

	@PostMapping("/plans")
	public ResponseEntity<List<PlanResponse>> getPlans(@RequestBody SearchRequest request) {
		List<PlanResponse> plans = service.searchPlans(request);
		return new ResponseEntity<>(plans, HttpStatus.OK);
	}

	@GetMapping("/plannames")
	public ResponseEntity<List<String>> getPlanNames() {
		List<String> planNames = service.getUniquePlanNames();
		return new ResponseEntity<List<String>>(planNames, HttpStatus.OK);
	}

	@GetMapping("/planstatus")
	public ResponseEntity<List<String>> getPlanStatus() {
		List<String> planStatus = service.getIniquePlanStatus();
		return new ResponseEntity<List<String>>(planStatus, HttpStatus.OK);
	}
}
