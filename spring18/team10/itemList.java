
import java.io.Serializable;
import java.util.ArrayList;

public class itemList implements Serializable {
	private ArrayList<String> categories;
	ArrayList<itemManage> items;
	public int[] tableNum;
	
	public itemList()
	{
		categories = new ArrayList<String>();
		items = new ArrayList<itemManage>();
		tableNum = new int[8];
	}
	
	// category 부분
	public void addCategory( String cName )
	{
		categories.add( cName );
		items.add( new itemManage( cName ) );
	}
	
	public void delCategory( String cName )
	{
		categories.remove( cName );
		
		for( itemManage tempItemM : items )
		{
			if( tempItemM.getCategory().equals( cName ) )
			{
				items.remove( tempItemM );
				return;
			}
		}
	}
	public void delCategory( int index )
	{
		categories.remove( index );
		items.remove( index );
	}
		
	public void modifyCategory( String cName, String modifyCName )
	{
		categories.set( categories.indexOf( cName ), modifyCName );
	}
	public void modifyCategory( int index, String modifyCName )
	{
		categories.set( index, modifyCName );
	}
	
	public void setCategoryOrder( String selectedCName, int changeIndex )
	{
		int selectedIndex = categories.indexOf( selectedCName );
		
		if( changeIndex < selectedIndex )
		{
			for( int i = selectedIndex; i > changeIndex; i-- )
			{
				categories.set( i, categories.get( i-1 ));
			}
			categories.set(changeIndex, selectedCName);
		}
		else
		{
			for( int i = selectedIndex; i < changeIndex; i++ )
			{
				categories.set( i, categories.get( i+1 ));
			}
			categories.set(changeIndex, selectedCName);
		}
	}
	public void setCategoryOrder( int selectedIndex, int changeIndex )
	{
		String selectedCName = categories.get( selectedIndex );
		
		if( changeIndex < selectedIndex )
		{
			for( int i = selectedIndex; i > changeIndex; i-- )
			{
				categories.set( i, categories.get( i-1 ));
			}
			categories.set(changeIndex, selectedCName);
		}
		else
		{
			for( int i = selectedIndex; i < changeIndex; i++ )
			{
				categories.set( i, categories.get( i+1 ));
			}
			categories.set(changeIndex, selectedCName);
		}
	}
	
	public int getCategoryOrder( String cName )
	{
		return categories.indexOf( cName );
	}
	
	public ArrayList<String> getCategories()
	{
		return categories;
	}
	
	// item부분
	public void addItem( String cName, String iName, int p, int c, boolean sO )
	{
		for( itemManage tempItemM : items )
		{
			if( tempItemM.getCategory().equals( cName ) )
			{
				tempItemM.addItem(iName, p, c, sO);
				return;
			}
		}
	}
	public void addItem( int cIndex, String iName, int p, int c, boolean sO )
	{
		items.get(cIndex).addItem(iName, p, c, sO);		
	}
	
	public void delItem( String cName, String iName )
	{
		for( itemManage tempItemM : items )
		{
			if( tempItemM.getCategory().equals( cName ) )
			{
				tempItemM.delItem(iName);
				return;
			}
		}
	}
	public void delItem( int cIndex, String iName )
	{
		items.get(cIndex).delItem(iName);
	}
	public void delItem( int cIndex, int iIndex )
	{
		items.get(cIndex).delItem(iIndex);
	}
	
	public void modifyItem( String cName, String iName, String modifyIName, int modifyP, int modifyC, boolean sO  )
	{
		for( itemManage tempItemM : items )
		{
			if( tempItemM.getCategory().equals( cName ) )
			{
				tempItemM.modifyItem(iName, modifyIName, modifyP, modifyC, sO);
				return;
			}
		}
	}
	public void modifyItem( int cIndex, String iName, String modifyIName, int modifyP, int modifyC, boolean sO  )
	{
		items.get(cIndex).modifyItem(iName, modifyIName, modifyP, modifyC, sO);
	}
	public void modifyItem( int cIndex, int iIndex, String modifyIName, int modifyP, int modifyC, boolean sO  )
	{
		items.get(cIndex).modifyItem(iIndex, modifyIName, modifyP, modifyC, sO);
	}
	
	public itemManage getItemM( String cName )
	{
		for( itemManage tempitemM : items )
		{
			if( tempitemM.getCategory().equals(cName) )
			{
				return tempitemM;
			}
		}
		
		return new itemManage( "" );
	}
	public itemManage getItemM( int cIndex )
	{
		return items.get( cIndex );
	}
}
