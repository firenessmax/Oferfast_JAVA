package facade;

import java.util.List;

import model.Medalla;

public interface MedallaFacade {

	public void create(Medalla entity);

	public void edit(Medalla entity);

	public void remove(Medalla entity);

	public Medalla find(Object id);

	public List<Medalla> findAll();

	public List<Medalla> findRange(int[] range);

	public int count();

}
