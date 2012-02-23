/**
 * @author Christian Nuesa
 * @version 1.0
 */

package com.temp.repository;


public interface RepositoryInterface<T> {
	public T getRowById(long id) ;
	public T addRow(T t) ;
}
