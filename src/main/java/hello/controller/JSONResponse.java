package hello.controller;

import java.util.Map;


public class JSONResponse {
		private boolean validated = true;
		private Map<String, String> errorMessages;
		
		
		public JSONResponse() {
			super();
		}
		
		
		public boolean isValidated() {
			return validated;
		}
		public void setValidated(boolean validated) {
			this.validated = validated;
		}
		public Map<String, String> getErrorMessages() {
			return errorMessages;
		}
		public void setErrorMessages(Map<String, String> errorMessages) {
			this.errorMessages = errorMessages;
		}
		@Override
		public String toString() {
			return "JSONResponse validated=" + validated + ", errorMessages="
					+ errorMessages + "]";
		}

}
