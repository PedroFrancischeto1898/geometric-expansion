class ThreadMudaFundo extends Thread {

    private final float h;
    private final float incremento;

    ThreadMudaFundo(float h, float incremento) {
        this.h = h;
        this.incremento = incremento;
    }

    @Override
    public void run() {
        while (!(
                JOGO.planoDeFundo.getH() >= h - Math.abs(2f * incremento)
                && JOGO.planoDeFundo.getH() <= h + Math.abs(2f * incremento)
                )
                && PainelDeJogo.jogador.getVivo()) {

            JOGO.planoDeFundo.setH(
                    JOGO.planoDeFundo.getH() + incremento
            );

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}