package com.example.struggler.firebasejava;

class Mahasiswa {

    private String nim;
    private String nama;
    private String semester;

    public Mahasiswa() {
    }

    public Mahasiswa(String nim, String nama, String semester) {
        this.nim = nim;
        this.nama = nama;
        this.semester = semester;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
