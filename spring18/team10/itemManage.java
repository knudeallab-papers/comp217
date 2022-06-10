
import java.util.ArrayList;
import java.io.Serializable;

public class itemManage implements Serializable {
	private ArrayList<item> items;
	private String category;
	
	public itemManage( String cName )
	{
		items = new ArrayList<item>();
		setCategory( cName );
	}
	
	public void addItem( String iName, int p, int c, boolean sO )
	{
		items.add( new item(iName, p, c, sO) );
	}
	
	public void delItem( String iName )
	{
		items.remove( getItemsOrder(iName) );
	}
	public void delItem( int index )
	{
		if( index < getSize() )
			items.remove( index );
	}
	
	public void modifyItem( String selectedIName, String changeIName,  int p, int c, boolean sO )
	{
		item tempItem = getItem(selectedIName);
		tempItem.setItemName(changeIName);
		tempItem.setSoldOut(sO);
		if( p >= 0 )
			tempItem.setPrice(p);
		if( c >= 0 )
			tempItem.setCost(c);
	}
	public void modifyItem( int selectedIIndex, String changeIName, int p, int c, boolean sO )
	{
		item tempItem = getItem(selectedIIndex);
		tempItem.setItemName(changeIName);
		tempItem.setSoldOut(sO);
		if( p >= 0 )
			tempItem.setPrice(p);
		if( c >= 0 )
			tempItem.setCost(c);
	}
	
	public item getItem( String iName )
	{
		for( item tempItem : items )
		{
			if( tempItem.getItemName().equals( iName ) )
			{
				return tempItem;
			}
		}
		return null;
	}
	public item getItem( int index )
	{
		return items.get(index);
	}
	
	
	public ArrayList<item> getItems()
	{
		return items;
	}
	
	public String[] getItemsNames()
	{
		String[] tempStringArray = new String[ getItems().size() ];
		
		for( int i = 0; i < getItems().size(); i++ )
			tempStringArray[i] = getItems().get(i).getItemName();
		
		return tempStringArray;
	}
	
	public void setItemOrder( String selectedIName, int changeIndex )
	{
		int selectedIndex = items.indexOf( selectedIName );
		item selectedItem = getItem(selectedIName);
		
		if( changeIndex < selectedIndex )
		{
			for( int i = selectedIndex; i > changeIndex; i-- )
			{
				items.set( i, items.get( i-1 ));
			}
			items.set(changeIndex, selectedItem);
		}
		else
		{
			for( int i = selectedIndex; i < changeIndex; i++ )
			{
				items.set( i, items.get( i+1 ));
			}
			items.set(changeIndex, selectedItem);
		}
	}
	public void setItemOrder( int selectedIndex, int changeIndex )
	{
		item selectedItem = getItem( selectedIndex );
		
		if( changeIndex < selectedIndex )
		{
			for( int i = selectedIndex; i > changeIndex; i-- )
			{
				items.set( i, items.get( i-1 ));
			}
			items.set(changeIndex, selectedItem);
		}
		else
		{
			for( int i = selectedIndex; i < changeIndex; i++ )
			{
				items.set( i, items.get( i+1 ));
			}
			items.set(changeIndex, selectedItem);
		}
	}
	
	public int getItemsOrder( String iName )
	{
		return items.indexOf( getItem(iName) );
	}
	
	public int getSize()
	{
		return items.size();
	}
	
	public void setCategory( String cName )
	{
		category = cName;
	}
	
	public String getCategory()
	{
		return category;
	}
}
