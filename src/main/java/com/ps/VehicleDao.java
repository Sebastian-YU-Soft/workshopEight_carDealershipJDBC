package com.ps;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


public class VehicleDao {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;


    public VehicleDao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("CarDealershipPU");
        entityManager = entityManagerFactory.createEntityManager();
    }


    public List<Vehicle> searchByPriceRange (float minPrice, float maxPrice) {
        return entityManager.createQuery("SELECT v FROM Vehicle v WHERE v.price BETWEEN minPrice AND maxPrice", Vehicle.class)
                .setParameter("minPrice",minPrice)
                .setParameter("maxPrice", maxPrice)
                .getResultList();
    }


    public List<Vehicle> searchByMakeModel(String make, String model) {
        return entityManager.createQuery("SELECT v FROM Vehicle v WHERE v.make = make AND v.model = model", Vehicle.class)
                .setParameter("make", make)
                .setParameter("model", model)
                .getResultList();
    }


    public List<Vehicle> searchByYearRange(int minYear, int maxYear) {
        return entityManager.createQuery("SELECT v FROM Vehicle v WHERE v.year BETWEEN :minYear AND :maxYear", Vehicle.class)
                .setParameter("minYear", minYear)
                .setParameter("maxYear", maxYear)
                .getResultList();
    }


    public List<Vehicle> searchByColor(String color) {
        return entityManager.createQuery("SELECT v FROM Vehicle v WHERE v.color = :color", Vehicle.class)
                .setParameter("color", color)
                .getResultList();
    }


    public List<Vehicle> searchByMileageRange(float minMileage, float maxMileage) {
        return entityManager.createQuery("SELECT v FROM Vehicle v WHERE v.mileage BETWEEN :minMileage AND :maxMileage", Vehicle.class)
                .setParameter("minMileage", minMileage)
                .setParameter("maxMileage", maxMileage)
                .getResultList();
    }


    public List<Vehicle> searchByType(String vehicleType) {
        return entityManager.createQuery("SELECT v FROM Vehicle v WHERE v.vehicleType = :vehicleType", Vehicle.class)
                .setParameter("vehicleType", vehicleType)
                .getResultList();
    }


    public void addVehicle(Vehicle vehicle) {
        entityManager.getTransaction().begin();
        entityManager.persist(vehicle);
        entityManager.getTransaction().commit();
    }


    public void removeVehicle(int vehicleId) {
        entityManager.getTransaction().begin();
        Vehicle vehicle = entityManager.find(Vehicle.class, vehicleId);
        if (vehicle != null) {
            entityManager.remove(vehicle);
        }
        entityManager.getTransaction().commit();
    }


    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
