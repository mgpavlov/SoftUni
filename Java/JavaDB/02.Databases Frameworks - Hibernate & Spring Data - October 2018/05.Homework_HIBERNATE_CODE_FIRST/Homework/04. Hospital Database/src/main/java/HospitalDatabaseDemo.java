import entities.Diagnose;
        import entities.Medicament;
        import entities.Patient;
        import entities.Visitation;

        import javax.persistence.EntityManager;
        import javax.persistence.EntityManagerFactory;
        import javax.persistence.Persistence;
        import java.util.Date;
        import java.util.HashSet;

public class HospitalDatabaseDemo {

    public static void main(String[] args) {

        Medicament medicament = new Medicament();
        medicament.setName("Analgin");
        medicament.setPatients(new HashSet<>());

        Medicament medicament2 = new Medicament();
        medicament2.setName("Analgin hinin");
        medicament2.setPatients(new HashSet<>());

        Diagnose diagnose = new Diagnose();
        diagnose.setName("Flu");
        diagnose.setComments("joke");
        diagnose.setPatients(new HashSet<>());

        Diagnose diagnose2 = new Diagnose();
        diagnose2.setName("Headache");
        diagnose2.setComments("not a joke");
        diagnose2.setPatients(new HashSet<>());

        Visitation visitation = new Visitation();
        visitation.setDate(new Date());
        visitation.setComments("visiting 1");

        Visitation visitation2 = new Visitation();
        visitation2.setDate(new Date());
        visitation2.setComments("visiting 2");

        Visitation visitation3 = new Visitation();
        visitation3.setDate(new Date());
        visitation3.setComments("visiting 3");

        Patient patient = new Patient();
        patient.setFirstName("Misho");
        patient.setLastName("Mishov");
        patient.setBirthDate(new Date());
        patient.setAddress("Sofia, Ilinden");
        patient.setHasMedicalInsurance(true);
        patient.setEmail("misho@gmail.com");
        /*patient.setPicture(new byte[8192]);*/
        patient.setDiagnoses(new HashSet<Diagnose>() {{
            add(diagnose);
            add(diagnose2);
        }});
        patient.setPrescriptions(new HashSet<Medicament>() {{
            add(medicament);
            add(medicament2);
        }});
        patient.setVisitations(new HashSet<Visitation>() {{
            add(visitation);
            add(visitation2);
        }});

        Patient patient2 = new Patient();
        patient2.setFirstName("Koko");
        patient2.setLastName("Kokev");
        patient2.setBirthDate(new Date());
        patient2.setAddress("Sofia, Lyulin");
        patient2.setHasMedicalInsurance(false);
        patient2.setEmail("koko@abv.bg");
       /* patient2.setPicture(new byte[50000]);*/
        patient2.setDiagnoses(new HashSet<Diagnose>() {{
            add(diagnose);
            add(diagnose2);
        }});
        patient2.setPrescriptions(new HashSet<Medicament>() {{
            add(medicament);
            add(medicament2);
        }});
        patient2.setVisitations(new HashSet<Visitation>() {{
            add(visitation3);
        }});

        diagnose.getPatients().add(patient);
        diagnose.getPatients().add(patient2);
        diagnose2.getPatients().add(patient);
        diagnose2.getPatients().add(patient2);

        medicament.getPatients().add(patient);
        medicament.getPatients().add(patient2);
        medicament2.getPatients().add(patient);
        medicament2.getPatients().add(patient2);

        visitation.setPatient(patient);
        visitation2.setPatient(patient);
        visitation3.setPatient(patient2);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hospital");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            entityManager.persist(patient);
            entityManager.persist(patient2);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
