function openPhotoModal(url) {
    var modal = document.getElementById('photoModal');
    var modalImage = document.getElementById('modalImage');
    modal.style.display = 'block';
    modalImage.src = url;
}

function closeModal() {
    document.getElementById('photoModal').style.display = 'none';
}

function openAddPhotoForm() {
    document.getElementById('addPhotoForm').style.display = 'block';
}
