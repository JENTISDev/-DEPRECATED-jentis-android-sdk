package com.jentis.tracking.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Cookie {

    String jtsLog, fbp, jtsRw, jtsFbp, jtsGaFeGid, jtsGaFeCid, fbc, jtsFb, ga, gclAu, gclAw, jAdfID, jctrSid, pkSes0066, gatGtagUA220398793_2, jtsGa, jtsGaGid, ctoBundle, ga3Z29BF112E;


    public String toJSON() {
        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("jts_log", getJtsLog());
            jsonObject.put("_fbp", getFbp());
            jsonObject.put("jts-rw", getJtsRw());
            jsonObject.put("jts-fbp", getJtsFbp());
            jsonObject.put("jts-ga-fe-gid", getJtsGaFeGid());
            jsonObject.put("jts-ga-fe-cid", getJtsGaFeCid());
            jsonObject.put("_fbc", getFbc());
            jsonObject.put("jts-fbc", getJtsFbp());//dfaf
            jsonObject.put("_ga", getGa());
            jsonObject.put("_gcl_au", getGclAu());//
            jsonObject.put("_gcl_aw", getGclAw());
            jsonObject.put("j_adf_id", getjAdfID());
            jsonObject.put("jctr_sid", getJctrSid());
            jsonObject.put("_pk_ses..0066", getPkSes0066());
            jsonObject.put("_gat_gtag_UA_220398793_2", getGatGtagUA220398793_2());
            jsonObject.put("jts_ga", getJtsGa());
            jsonObject.put("jts_ga_gid", getJtsGaGid());
            jsonObject.put("cto_bundle", getCtoBundle());
            jsonObject.put("_ga_3Z29BF112E", getGa3Z29BF112E());

            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public Cookie() {}

    public Cookie(String jtsLog, String fbp, String jtsRw, String jtsFbp, String jtsGaFeGid, String jtsGaFeCid, String fbc, String jtsFb, String ga, String gclAu, String gclAw, String jAdfID, String jctrSid, String pkSes0066, String gatGtagUA220398793_2, String jtsGa, String jtsGaGid, String ctoBundle, String ga3Z29BF112E) {
        this.jtsLog = jtsLog;
        this.fbp = fbp;
        this.jtsRw = jtsRw;
        this.jtsFbp = jtsFbp;
        this.jtsGaFeGid = jtsGaFeGid;
        this.jtsGaFeCid = jtsGaFeCid;
        this.fbc = fbc;
        this.jtsFb = jtsFb;
        this.ga = ga;
        this.gclAu = gclAu;
        this.gclAw = gclAw;
        this.jAdfID = jAdfID;
        this.jctrSid = jctrSid;
        this.pkSes0066 = pkSes0066;
        this.gatGtagUA220398793_2 = gatGtagUA220398793_2;
        this.jtsGa = jtsGa;
        this.jtsGaGid = jtsGaGid;
        this.ctoBundle = ctoBundle;
        this.ga3Z29BF112E = ga3Z29BF112E;
    }

    public String getJtsLog() {
        return jtsLog;
    }

    public void setJtsLog(String jtsLog) {
        this.jtsLog = jtsLog;
    }

    public String getFbp() {
        return fbp;
    }

    public void setFbp(String fbp) {
        this.fbp = fbp;
    }

    public String getJtsRw() {
        return jtsRw;
    }

    public void setJtsRw(String jtsRw) {
        this.jtsRw = jtsRw;
    }

    public String getJtsFbp() {
        return jtsFbp;
    }

    public void setJtsFbp(String jtsFbp) {
        this.jtsFbp = jtsFbp;
    }

    public String getJtsGaFeGid() {
        return jtsGaFeGid;
    }

    public void setJtsGaFeGid(String jtsGaFeGid) {
        this.jtsGaFeGid = jtsGaFeGid;
    }

    public String getJtsGaFeCid() {
        return jtsGaFeCid;
    }

    public void setJtsGaFeCid(String jtsGaFeCid) {
        this.jtsGaFeCid = jtsGaFeCid;
    }

    public String getFbc() {
        return fbc;
    }

    public void setFbc(String fbc) {
        this.fbc = fbc;
    }

    public String getJtsFb() {
        return jtsFb;
    }

    public void setJtsFb(String jtsFb) {
        this.jtsFb = jtsFb;
    }

    public String getGa() {
        return ga;
    }

    public void setGa(String ga) {
        this.ga = ga;
    }

    public String getGclAu() {
        return gclAu;
    }

    public void setGclAu(String gclAu) {
        this.gclAu = gclAu;
    }

    public String getGclAw() {
        return gclAw;
    }

    public void setGclAw(String gclAw) {
        this.gclAw = gclAw;
    }

    public String getjAdfID() {
        return jAdfID;
    }

    public void setjAdfID(String jAdfID) {
        this.jAdfID = jAdfID;
    }

    public String getJctrSid() {
        return jctrSid;
    }

    public void setJctrSid(String jctrSid) {
        this.jctrSid = jctrSid;
    }

    public String getPkSes0066() {
        return pkSes0066;
    }

    public void setPkSes0066(String pkSes0066) {
        this.pkSes0066 = pkSes0066;
    }

    public String getGatGtagUA220398793_2() {
        return gatGtagUA220398793_2;
    }

    public void setGatGtagUA220398793_2(String gatGtagUA220398793_2) {
        this.gatGtagUA220398793_2 = gatGtagUA220398793_2;
    }

    public String getJtsGa() {
        return jtsGa;
    }

    public void setJtsGa(String jtsGa) {
        this.jtsGa = jtsGa;
    }

    public String getJtsGaGid() {
        return jtsGaGid;
    }

    public void setJtsGaGid(String jtsGaGid) {
        this.jtsGaGid = jtsGaGid;
    }

    public String getCtoBundle() {
        return ctoBundle;
    }

    public void setCtoBundle(String ctoBundle) {
        this.ctoBundle = ctoBundle;
    }

    public String getGa3Z29BF112E() {
        return ga3Z29BF112E;
    }

    public void setGa3Z29BF112E(String ga3Z29BF112E) {
        this.ga3Z29BF112E = ga3Z29BF112E;
    }
}
