/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aaTag;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Gustavo Henrique
 */
public class aaTagMethods {

    private aaTagRequest Request;
    
    public aaTagMethods(String Server)    {
      Request = new aaTagRequest(Server);
    }

    public aaTagReturn AddTag(aaTagOAuth OAuthDefault, Tag tag) throws UnsupportedEncodingException, IOException{
      OAuthDefault.getOauth().setMethod(enmMethods.AddTag.toString());
      OAuthDefault.getOauth().setParameters(new Object[] { tag } );
      return Request.getMethod(OAuthDefault, "");
    }

    public aaTagReturn AddEvent(aaTagOAuth OAuthDefault, Event evento) throws UnsupportedEncodingException, IOException{
      OAuthDefault.getOauth().setMethod(enmMethods.AddEvent.toString());
      OAuthDefault.getOauth().setParameters(new Object[] { evento } );
      return Request.getMethod(OAuthDefault, "");
    }

    public aaTagReturn AddApplication(aaTagOAuth OAuthDefault, Application application) throws UnsupportedEncodingException, IOException {
      OAuthDefault.getOauth().setMethod(enmMethods.AddApplication.toString() );
      OAuthDefault.getOauth().setParameters(new Object[] { application } );
      return Request.getMethod(OAuthDefault, "");
    }

    public aaTagReturn AddRegister(aaTagOAuth OAuthDefault, String EventName, String CodTag) throws UnsupportedEncodingException, IOException {
      OAuthDefault.getOauth().setMethod(enmMethods.AddRegister.toString());
      OAuthDefault.getOauth().setParameters( new Object[] { EventName, CodTag } );
      return Request.getMethod(OAuthDefault, "");
    }

    public aaTagReturn AlterUser(aaTagOAuth OAuthDefault, User User) throws UnsupportedEncodingException, IOException {
      OAuthDefault.getOauth().setMethod(enmMethods.AlterUser.toString() );
      OAuthDefault.getOauth().setParameters(new Object[] { User } );
      return Request.getMethod(OAuthDefault, "");
    }

    public aaTagReturn AlterEvent(aaTagOAuth OAuthDefault, Event evento, String nomeEvento) throws UnsupportedEncodingException, IOException {
      OAuthDefault.getOauth().setMethod(enmMethods.AlterEvent.toString() );
      OAuthDefault.getOauth().setParameters(new Object[] { nomeEvento, evento } );
      return Request.getMethod(OAuthDefault, "");
    }

    public aaTagReturn AlterApplication(aaTagOAuth OAuthDefault) throws UnsupportedEncodingException, IOException {
      OAuthDefault.getOauth().setMethod(enmMethods.AlterApplication.toString());
      OAuthDefault.getOauth().setParameters(new Object[] { });
      return Request.getMethod(OAuthDefault);
    }

    public aaTagReturn GetUserInfo(aaTagOAuth OAuthDefault) throws UnsupportedEncodingException, IOException {
      OAuthDefault.getOauth().setMethod(enmMethods.GetUserInfo.toString());
      OAuthDefault.getOauth().setParameters(new Object[] { });
      return Request.getMethod(OAuthDefault, "User");
    }

    public aaTagReturn GetUser(aaTagOAuth OAuthDefault, String CodTag) throws UnsupportedEncodingException, IOException {
      OAuthDefault.getOauth().setMethod(enmMethods.GetUser.toString());
      OAuthDefault.getOauth().setParameters(new Object[] { CodTag });
       return Request.getMethod(OAuthDefault, "User");
    }

    public aaTagReturn GetUser(aaTagOAuth OAuthDefault) throws UnsupportedEncodingException, IOException {
      OAuthDefault.getOauth().setMethod(enmMethods.GetUser.toString());
      OAuthDefault.getOauth().setParameters(new Object[] {  });
       return Request.getMethod(OAuthDefault, "User");
    }

    public aaTagReturn GetTag(aaTagOAuth OAuthDefault, String codPublicoTAG) throws UnsupportedEncodingException, IOException {
      OAuthDefault.getOauth().setMethod(enmMethods.GetTag.toString());
      OAuthDefault.getOauth().setParameters(new Object[] { codPublicoTAG });
      return Request.getMethod(OAuthDefault, "Tag" );
    }

    public aaTagReturn GetTagInfo(aaTagOAuth OAuthDefault, String codTag) throws UnsupportedEncodingException, IOException {
      OAuthDefault.getOauth().setMethod(enmMethods.GetTagInfo.toString());
      OAuthDefault.getOauth().setParameters(new Object[] { codTag });
      return Request.getMethod(OAuthDefault, "Tag");
    }

    public aaTagReturn GetEvent(aaTagOAuth OAuthDefault, String nomeEvento) throws UnsupportedEncodingException, IOException {
      OAuthDefault.getOauth().setMethod(enmMethods.GetEvent.toString());
      OAuthDefault.getOauth().setParameters(new Object[] { nomeEvento });
      return Request.getMethod(OAuthDefault, "Event");
    }

    public aaTagReturn GetApplication(aaTagOAuth OAuthDefault, String nomeAplicacao) throws UnsupportedEncodingException, IOException {
      OAuthDefault.getOauth().setMethod(enmMethods.GetApplication.toString());
      OAuthDefault.getOauth().setParameters(new Object[] { nomeAplicacao });
      return Request.getMethod(OAuthDefault, "Application");
    }

    public aaTagReturn GetTags(aaTagOAuth OAuthDefault) throws UnsupportedEncodingException, IOException {
      OAuthDefault.getOauth().setMethod(enmMethods.GetTags.toString());
      OAuthDefault.getOauth().setParameters(new Object[] { });
      return Request.getMethod(OAuthDefault);
    }

    public aaTagReturn GetEvents(aaTagOAuth OAuthDefault) throws UnsupportedEncodingException, IOException {
      OAuthDefault.getOauth().setMethod(enmMethods.GetEvents.toString());
      OAuthDefault.getOauth().setParameters(new Object[] { });
      return Request.getMethod(OAuthDefault, "ArrayListEvent");
    }

    public aaTagReturn RemoveTag(aaTagOAuth OAuthDefault, String codTag) throws UnsupportedEncodingException, IOException {
      OAuthDefault.getOauth().setMethod(enmMethods.RemoveTag.toString());
      OAuthDefault.getOauth().setParameters(new Object[] { codTag });
      return Request.getMethod(OAuthDefault, "");
    }

    public aaTagReturn RemoveEvent(aaTagOAuth OAuthDefault, String nomeEvento) throws UnsupportedEncodingException, IOException {
      OAuthDefault.getOauth().setMethod(enmMethods.RemoveEvent.toString());
      OAuthDefault.getOauth().setParameters( new Object[] { nomeEvento });
      return Request.getMethod(OAuthDefault, "");
    }

    public aaTagReturn InactivateTag(aaTagOAuth OAuthDefault, String codTag) throws UnsupportedEncodingException, IOException {
      OAuthDefault.getOauth().setMethod(enmMethods.InactivateTag.toString());
      OAuthDefault.getOauth().setParameters( new Object[] { codTag } );
      return Request.getMethod(OAuthDefault, "");
    }

    public aaTagReturn GetUserRegisters(aaTagOAuth OAuthDefault) throws UnsupportedEncodingException, IOException {
      OAuthDefault.getOauth().setMethod(enmMethods.GetUserRegisters.toString() );
      OAuthDefault.getOauth().setParameters(new Object[] { });
      return Request.getMethod(OAuthDefault);
    }

    public aaTagReturn GetEventRegisters(aaTagOAuth OAuthDefault) throws UnsupportedEncodingException, IOException {
      OAuthDefault.getOauth().setMethod( enmMethods.GetEventRegisters.toString() );
      OAuthDefault.getOauth().setParameters(new Object[] { } );
      return Request.getMethod(OAuthDefault);
    }

    public aaTagReturn PostFacebook(aaTagOAuth OAuthDefault, String Message) throws UnsupportedEncodingException, IOException {
      OAuthDefault.getOauth().setMethod(enmMethods.PostFacebook.toString());
      OAuthDefault.getOauth().setParameters( new Object[] { Message });
      return Request.getMethod(OAuthDefault, "");
    }

    public aaTagReturn PostFacebook(aaTagOAuth OAuthDefault, String Message, String CodTag) throws UnsupportedEncodingException, IOException {
      OAuthDefault.getOauth().setMethod(enmMethods.PostFacebook.toString());
      OAuthDefault.getOauth().setParameters(new Object[] { Message, CodTag });
      return Request.getMethod(OAuthDefault);
    }

    public aaTagReturn PostTwitter(aaTagOAuth OAuthDefault, String Message) throws UnsupportedEncodingException, IOException {
      OAuthDefault.getOauth().setMethod(enmMethods.PostTwitter.toString());
      OAuthDefault.getOauth().setParameters(new Object[] { Message });
      return Request.getMethod(OAuthDefault, "");
    }

    public aaTagReturn PostTwitter(aaTagOAuth OAuthDefault, String Message, String CodTag) throws UnsupportedEncodingException, IOException {
      OAuthDefault.getOauth().setMethod(enmMethods.PostTwitter.toString());
      OAuthDefault.getOauth().setParameters( new Object[] { Message, CodTag } );
      return Request.getMethod(OAuthDefault, "");
    }
}
