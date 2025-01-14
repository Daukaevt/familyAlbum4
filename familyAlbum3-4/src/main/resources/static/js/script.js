document.addEventListener("DOMContentLoaded", () => {
    const toolbar = document.getElementById("toolbar");
    const addPhotoIcon = document.getElementById("add-photo-icon");
    const addPhotoForm = document.getElementById("add-photo-form");
    const tiles = document.querySelectorAll(".photo-tile img");

    const modal = document.getElementById("photo-modal");
    const modalImage = document.getElementById("modal-image");
    const closeModal = document.getElementById("close-modal");
    const modalDescription = document.getElementById("photo-description-modal");

    tiles.forEach((img) => {
        // Открытие модального окна при клике
        img.addEventListener("click", () => {
            modal.style.display = "flex";
            modalImage.src = img.src;
            modalDescription.textContent = img.alt || "No description available";

            // Добавляем класс для анимации увеличения
            setTimeout(() => {
                modal.classList.add("show");
            }, 10);
        });
    });

    // Закрытие модального окна
    closeModal.addEventListener("click", () => {
        modal.classList.remove("show");
        setTimeout(() => {
            modal.style.display = "none";
        }, 300); // Ждем завершения анимации
    });

    // Закрытие окна при клике вне изображения
    modal.addEventListener("click", (e) => {
        if (e.target === modal) {
            modal.classList.remove("show");
            setTimeout(() => {
                modal.style.display = "none";
            }, 300);
        }
    });

    // Показать тулбар при клике на иконку
    addPhotoIcon.addEventListener("click", () => {
        toolbar.style.display = "flex";
    });

    // Скрыть тулбар и отправить данные при сабмите
    addPhotoForm.addEventListener("submit", async (e) => {
        e.preventDefault();

        const photoUrl = document.getElementById("photo-url").value;
        const photoDescription = document.getElementById("photo-description").value;

        const response = await fetch("/api/photos/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                url: photoUrl,
                description: photoDescription,
            }),
        });

        if (response.ok) {
            toolbar.style.display = "none";
            addPhotoForm.reset();
            alert("Photo added successfully!");
            location.reload();
        } else {
            alert("Failed to add photo. Please try again.");
        }
    });
});


