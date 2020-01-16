/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Conection;

import Anime._Anime;
import com.mongodb.Cursor;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JOptionPane;
import org.bson.Document;


/**
 *
 * @author osouza
 */
public class MongoConnect {
 
    public static void connectionToMongoDB(String[] n)
    {
        try
        {
            MongoClientURI uri = new MongoClientURI("mongodb+srv://osouza:azxboe1801@cluster0-scgk3.gcp.mongodb.net/MongoDB?retryWrites=true&w=majority");
            MongoClient mongoClient = new MongoClient(uri);        
            MongoDatabase database = mongoClient.getDatabase("MongoDB");                        
            MongoCollection collection = database.getCollection("animes");
            Document doc = new Document("nome", n[0]);
            doc.append("episodios", n[1]);
            doc.append("ano", n[2]);
            doc.append("temporada", n[3]);
            collection.insertOne(doc);
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Erro ao adicionar -> "+ e);
        }
        
    }
    
    public List<_Anime> selectAllRecordsFromACollection() 
    {
        MongoClientURI uri = new MongoClientURI("mongodb+srv://osouza:azxboe1801@cluster0-scgk3.gcp.mongodb.net/MongoDB?retryWrites=true&w=majority");
        MongoClient mongoClient = new MongoClient(uri);        
        MongoDatabase database = mongoClient.getDatabase("MongoDB");                        
        MongoCollection collection = database.getCollection("animes");
        List<_Anime> animes = new ArrayList<>();
        
        try (MongoCursor<Document> cur = collection.find().iterator())
        {

            while (cur.hasNext()) {

                _Anime a = new _Anime();
                
                Document doc = cur.next();
               
                a.setNome( doc.get("nome").toString() );
                a.setEpisodios( doc.get("episodios").toString() );
                a.setTemporadas( doc.get("temporada").toString() );
                a.setAno( doc.get("ano").toString() );                
                
                animes.add(a);
            }
            
            return animes;
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Ocorreu um erro ao buscar dados do banco -> "+ e);
            return null;
        }
    }

    
}
