package ru.systematic.firstTrainingApp.model;

public enum Role {

    ADMIN{
        @Override
        public String toString() {
            return "Администратор";
        }
    },
    USER{
        @Override
        public String toString() {
            return "Пользователь";
        }
    },
    TEST_USER{
        @Override
        public String toString() {
            return "Тестовый пользователь";
        }
    }
}
