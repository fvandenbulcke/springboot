//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.3.0 
// Voir <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2018.09.26 à 11:59:14 AM CEST 
//


package sb.api.webservice.soap;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the sb.api.webservice.soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: sb.api.webservice.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetStudentByIdRequest }
     * 
     */
    public GetStudentByIdRequest createGetStudentByIdRequest() {
        return new GetStudentByIdRequest();
    }

    /**
     * Create an instance of {@link GetStudentByIdResponse }
     * 
     */
    public GetStudentByIdResponse createGetStudentByIdResponse() {
        return new GetStudentByIdResponse();
    }

    /**
     * Create an instance of {@link StudentDetails }
     * 
     */
    public StudentDetails createStudentDetails() {
        return new StudentDetails();
    }

    /**
     * Create an instance of {@link SearchStudentsRequest }
     * 
     */
    public SearchStudentsRequest createSearchStudentsRequest() {
        return new SearchStudentsRequest();
    }

    /**
     * Create an instance of {@link SearchStudentsResponse }
     * 
     */
    public SearchStudentsResponse createSearchStudentsResponse() {
        return new SearchStudentsResponse();
    }

    /**
     * Create an instance of {@link CreateStudentRequest }
     * 
     */
    public CreateStudentRequest createCreateStudentRequest() {
        return new CreateStudentRequest();
    }

    /**
     * Create an instance of {@link CreateStudentResponse }
     * 
     */
    public CreateStudentResponse createCreateStudentResponse() {
        return new CreateStudentResponse();
    }

    /**
     * Create an instance of {@link UpdateStudentNameRequest }
     * 
     */
    public UpdateStudentNameRequest createUpdateStudentNameRequest() {
        return new UpdateStudentNameRequest();
    }

    /**
     * Create an instance of {@link UpdateStudentNameResponse }
     * 
     */
    public UpdateStudentNameResponse createUpdateStudentNameResponse() {
        return new UpdateStudentNameResponse();
    }

    /**
     * Create an instance of {@link DeleteStudentRequest }
     * 
     */
    public DeleteStudentRequest createDeleteStudentRequest() {
        return new DeleteStudentRequest();
    }

    /**
     * Create an instance of {@link DeleteStudentResponse }
     * 
     */
    public DeleteStudentResponse createDeleteStudentResponse() {
        return new DeleteStudentResponse();
    }

}
