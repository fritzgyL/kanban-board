export function closeModal(modalId: string) {
    const element = window.document.getElementById(modalId);
    if (element !== null) {
        element.click();
    }
}
