package src.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class that implements paging over a collection.
 * <a href="http://www.java2s.com/Code/Java/Collections-Data-Structure/Pagingoveracollection.htm">Récupéré du site suivant</a> 
 * 
 * @author Simon Brown
 */
public class Pageable<T> implements Cloneable{

	/** the default page size */
	public static final int DEFAULT_PAGE_SIZE = 10;

	private static final int PAGE_WINDOW = 10;

	/** the list over which this class is paging */
	private List<T> list;

	/** the page size */
	private int pageSize = DEFAULT_PAGE_SIZE;

	/** the current page */
	private int page;

	/** the starting index */
	private int startingIndex;

	/** the ending index */
	private int endingIndex;

	/** the maximum number of pages */
	private int maxPages;

	/**
	 * Creates a new instance with the specified list.
	 * 
	 * @param list
	 *            a List
	 */
	public Pageable(List<T> list) {
		setList(list);
	}

	private void calculatePages() {
		if (pageSize > 0) {
			// calculate how many pages there are
			if (list.size() % pageSize == 0) {
				maxPages = list.size() / pageSize;
			} else {
				maxPages = (list.size() / pageSize) + 1;
			}
		}
	}

	/**
	 * Gets the list that this instance is paging over.
	 * 
	 * @return a List
	 */
	public List<T> getList() {
		return this.list;
	}
	
	/**
	 * Sets the list 
	 * @param list
	 */
	public void setList(List<T> list) {
		this.list = (list != null)? list: new ArrayList<T>(); // Modification KV si la liste est nulle
		this.page = 1;
		this.maxPages = 1;

		calculatePages();
	}

	/**
	 * Gets the subset of the list for the current page.
	 * 
	 * @return a List
	 */
	public List<T> getListForPage() {
		return list.subList(startingIndex, endingIndex);
	}

	/**
	 * Gets the page size.
	 * 
	 * @return the page size as an int
	 */
	public int getPageSize() {
		return this.pageSize;
	}

	/**
	 * Sets the page size.
	 * 
	 * @param pageSize
	 *            the page size as an int
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		calculatePages();
	}

	/**
	 * Gets the page.
	 * 
	 * @return the page as an int
	 */
	public int getPage() {
		return this.page;
	}

	/**
	 * Sets the page size.
	 * 
	 * @param p
	 *            the page as an int
	 */
	public void setPage(int p) {
		if (p >= maxPages) {
			this.page = maxPages;
		} else if (p <= 1) {
			this.page = 1;
		} else {
			this.page = p;
		}

		// now work out where the sub-list should start and end
		startingIndex = pageSize * (page - 1);
		if (startingIndex < 0) {
			startingIndex = 0;
		}
		endingIndex = startingIndex + pageSize;
		if (endingIndex > list.size()) {
			endingIndex = list.size();
		}
	}

	/**
	 * Gets the maximum number of pages.
	 * 
	 * @return the maximum number of pages as an int
	 */
	public int getMaxPages() {
		return this.maxPages;
	}

	/**
	 * Determines whether there is a previous page and gets the page number.
	 * 
	 * @return the previous page number, or zero
	 */
	public int getPreviousPage() {
		if (page > 1) {
			return page - 1;
		} else {
			return 0;
		}
	}

	/**
	 * Determines whether there is a next page and gets the page number.
	 * 
	 * @return the next page number, or 0
	 */
	public int getNextPage() {
		if (page < maxPages) {
			return page + 1;
		} else {
			return 0;
		}
	}

	/**
	 * Gets the minimum page in the window.
	 * 
	 * @return the page number
	 */
	public int getMinPageRange() {
		if (getPage() > PAGE_WINDOW) {
			return getPage() - PAGE_WINDOW;
		} else {
			return 1;
		}
	}

	/**
	 * Gets the maximum page in the window.
	 * 
	 * @return the page number
	 */
	public int getMaxPageRange() {
		if (getPage() < (getMaxPages() - PAGE_WINDOW)) {
			return getPage() + PAGE_WINDOW;
		} else {
			return getMaxPages();
		}
	}

	/**
	 * Permet de déterminer s'il y a une page précédente, par rapport à la sélection actuelle
	 * @author vaillant
	 * @return true s'il y a une page précédente
	 */
	public boolean hasPreviousPage() {
		return (getPreviousPage() != 0);
	}

	/**
	 * Permet de déterminer s'il y a une page suivante, par rapport à la sélection actuelle
	 * @author vaillant
	 * @return true s'il y a une page suivante
	 */
	public boolean hasNextPage() {
		return (getNextPage() != 0);
	}
	
	/**
	 * Renvoie la liste des pages disponibles
	 * @author vaillant
	 * @return
	 */
	public List<Page> getListePages(){
		List<Page> listePages = new ArrayList<Page>();
		for(int i = 1; i <= this.maxPages; i++){
			listePages.add(new Page(""+i, "Page "+i));
		}
		return listePages;
	}
	
	@SuppressWarnings("unchecked")
	public Object clone() {
		Pageable<T> o = null;
    	try {
      		// On récupère l'instance à renvoyer par l'appel de la 
      		// méthode super.clone()
      		o = (Pageable<T>) super.clone();
    	} catch(CloneNotSupportedException cnse) {
      		// Ne devrait jamais arriver car nous implémentons 
      		// l'interface Cloneable
      		cnse.printStackTrace();
	    }
    	if(this.list != null)
    		o.list = (List<T>) ((ArrayList<T>)this.list).clone();
	    
    	o.setPage(this.getPage());
    	// on renvoie le clone
	    return o;
  	}
}