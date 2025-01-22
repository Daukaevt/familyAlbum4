function openModal(type) {
    const modal = document.getElementById('addModal');
    const backdrop = document.getElementById('backdrop');
    modal.classList.add('show');
    backdrop.classList.add('show');
}

function closeModals() {
    const modals = document.querySelectorAll('.modal');
    const backdrop = document.getElementById('backdrop');
    modals.forEach(modal => modal.classList.remove('show'));
    backdrop.classList.remove('show');
}

function openPhoto(url) {
    const modal = document.createElement('div');
    modal.style.position = 'fixed';
    modal.style.top = 0;
    modal.style.left = 0;
    modal.style.width = '100vw';
    modal.style.height = '100vh';
    modal.style.background = `rgba(0, 0, 0, 0.9) url(${url}) no-repeat center center`;
    modal.style.backgroundSize = 'contain';
    modal.style.zIndex = 1000;
    modal.onclick = () => modal.remove();
    document.body.appendChild(modal);
}