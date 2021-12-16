package dao.impl;

import bean.Country;
import dao.inter.AbstractDao;
import dao.inter.CountryDaoInter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl extends AbstractDao implements CountryDaoInter {


    private Country getCountry(ResultSet rs) throws Exception{
        int id=rs.getInt("id");
        String name=rs.getString("name");
        String nationality=rs.getString("nationality");
       Country country=new Country(id,name,nationality);
        return country;


    }

    @Override
    public List<Country> getAllCountry() {
        List<Country> result = new ArrayList<>();
        try( Connection c = connect()) {
        Statement stmt=c.createStatement();
        stmt.execute("Select * from country");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
              Country country=getCountry(rs);
                result.add(country);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}



