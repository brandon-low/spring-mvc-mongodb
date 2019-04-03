package hello;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hello.entity.Page;

public class PageTest {
	
	 private static final Logger log = LoggerFactory.getLogger(PageTest.class);
	
	private Example populate(Example e) {
		if (e == null) {
			e = new Example();
		}
		e.setName("Big Boy " + System.currentTimeMillis());
		e.setStatus(System.currentTimeMillis());
		e.setCreateUser("Brandon");
		e.setCreateTimestamp(new Date(System.currentTimeMillis()));
		e.setCreateUser("Brandon");
		e.setUpdateTimestamp(new Date(System.currentTimeMillis()));
			
		return e;
	}


	@Test
	public void testArrayList() {
		//fail("Not yet implemented");
		ArrayList list = new ArrayList();
		
		for (int i = 0; i < 10; i++) {
			list.add("list order=" + i);
		}
		
		for (int i = 0; i < list.size(); i++) {
			log.debug(list.get(i).toString());
		}
		assertTrue(true);
	}
	
	@Test
	public void testGetNumberOfPages()	{
		Page page = new Page();
		
		int row = 5;
		int lastPageNumber;
	
	
		page.setRowCount(row);
		lastPageNumber = page.getNumberOfPages();
		log.info("lastPageNumber(1)=" + lastPageNumber + " row="+ row + " pageSize=" + page.getPageSize());
	  
		assertTrue(lastPageNumber== 1);
		
		row = 10;
		page.setRowCount(row);
		lastPageNumber = page.getNumberOfPages();
		log.info("lastPageNumber(1)=" + lastPageNumber + " row="+ row + " pageSize=" + page.getPageSize());
	  
		assertTrue(lastPageNumber== 1);
	  row = 11;
	  page.setRowCount(row);
		lastPageNumber = page.getNumberOfPages();
		log.info("lastPageNumber(2)=" + lastPageNumber + " row="+ row + " pageSize=" + page.getPageSize());
	  
		assertTrue(lastPageNumber== 2);
	  row = 17;
	  page.setRowCount(row);
		lastPageNumber = page.getNumberOfPages();
		log.info("lastPageNumber(2)=" + lastPageNumber + " row="+ row + " pageSize=" + page.getPageSize());
		assertTrue(lastPageNumber== 2);
	  
	  
	  row = 20;
	  page.setRowCount(row);
		lastPageNumber = page.getNumberOfPages();
		log.info("lastPageNumber(2)=" + lastPageNumber + " row="+ row + " pageSize=" + page.getPageSize());
	  
		assertTrue(lastPageNumber== 2);
		
	  row = 21;
	  page.setRowCount(row);
		lastPageNumber = page.getNumberOfPages();
		log.info("lastPageNumber(3)=" + lastPageNumber + " row="+ row + " pageSize=" + page.getPageSize());
	  
		assertTrue(lastPageNumber== 3);
	 
	}
		
	private int getNumberOfPages(int row, int pageSize) {
		try {
			int r = row % pageSize;
			int d = row / pageSize;			
			return (r > 0) ? ( d + 1) : d;
			
		} catch (Exception e) {			
		}
		return -1;		
	}
	
	
	
	//@Test
	public void testMaths() {
		int lastPageNumber, row, pageSize;
			pageSize = 10;
		
			row = 5;
		
		  lastPageNumber =getNumberOfPages(row, pageSize);
		  log.info("lastPageNumber(1)=" + lastPageNumber + " row="+ row + " pageSize=" + pageSize);
		  row = 10;
		  lastPageNumber =getNumberOfPages(row, pageSize);
		  log.info("lastPageNumber(1)=" + lastPageNumber + " row="+ row + " pageSize=" + pageSize);
		
		  row = 11;
		  lastPageNumber =getNumberOfPages(row, pageSize);
		  log.info("lastPageNumber(2)=" + lastPageNumber + " row="+ row + " pageSize=" + pageSize);
		  
		  row = 17;
		  lastPageNumber =getNumberOfPages(row, pageSize);
		  log.info("lastPageNumber(2)=" + lastPageNumber + " row="+ row + " pageSize=" + pageSize);
		  row = 20;
		  lastPageNumber =getNumberOfPages(row, pageSize);
		  log.info("lastPageNumber(2)=" + lastPageNumber + " row="+ row + " pageSize=" + pageSize);
		
		  row = 21;
		  lastPageNumber =getNumberOfPages(row, pageSize);
		  log.info("lastPageNumber(3)=" + lastPageNumber + " row="+ row + " pageSize=" + pageSize);
		  
		  assertTrue(true);
	}

}
